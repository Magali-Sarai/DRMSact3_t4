package com.informatingconfig.spring.app1.springboot_applications.repositories;

import com.informatingconfig.spring.app1.springboot_applications.Models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}