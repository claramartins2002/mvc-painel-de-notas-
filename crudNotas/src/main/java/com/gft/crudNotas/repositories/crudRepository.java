package com.gft.crudNotas.repositories;

import com.gft.crudNotas.entities.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface crudRepository extends JpaRepository<Nota, Long> {
}
