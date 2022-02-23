package com.gft.crudNotas.controllers;


import com.gft.crudNotas.entities.Nota;
import com.gft.crudNotas.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("nota")
public class NotaController {
    @Autowired
private NotaService notaService;


    @RequestMapping(path = "novo")
    public ModelAndView novaNota() {
        ModelAndView mv = new ModelAndView("/nota/novo.html");
        mv.addObject("nota", new Nota());

        return mv;
    }

    @RequestMapping(path = "novo", method = RequestMethod.POST)
    public ModelAndView salvarNota(@Valid Nota nota, BindingResult bindingresult){
        ModelAndView mv = new ModelAndView("/nota/novo.html");
        boolean novo = true;

        if(nota.getId() !=null){
            novo = false;
        }
        if(bindingresult.hasErrors()){
            mv.addObject("nota", mv);
            return mv;
        }

        Nota notaSalva= notaService.salvarNota(nota);
        if(novo) {
            mv.addObject("nota", new Nota());
        }
        else{
            mv.addObject("nota", nota);
        }
        mv.addObject("mensagem", "Nota salva com sucesso!!");
        return mv;

    }

    @RequestMapping
    public ModelAndView listarNotas() {

        ModelAndView mv = new ModelAndView("/nota/listar.html");
        mv.addObject("lista", notaService.listarNota());

        return mv;
    }






    @RequestMapping("/editar")
    public ModelAndView editarNota(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("nota/novo.html");
        Nota nota;

        try{
            nota = notaService.obterNota(id);

        }
        catch(Exception e){
            nota = new Nota();
            mv.addObject("mensagem", e.getMessage());

        }
        mv.addObject("nota", nota);
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirNota(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/nota");
        Nota nota;

        try{
            notaService.excluirNota(id);
            redirectAttributes.addFlashAttribute("mensagem", "Nota excluída!");
        }
        catch(Exception e){
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir!"+e.getMessage());

        }
        return mv;
    }
}
