package com.gft.crudNotas.services;


import com.gft.crudNotas.entities.Nota;
import com.gft.crudNotas.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService {
    @Autowired
    private NotaRepository notaRepository;


    public Nota salvarNota(Nota nota){
        return notaRepository.save(nota);

    }




    public List<Nota> listarNota() {
        return notaRepository.findAll();
    }
    public Nota obterNota(Long id) throws Exception{
        Optional<Nota> nota = notaRepository.findById(id);

        if(nota.isEmpty()){
            throw new Exception("Nota não encontrada!");

        }
        return nota.get();
    }

    public void excluirNota(Long id){
        notaRepository.deleteById(id);
    }
}
