package com.informatingconfig.spring.app1.springboot_applications.repositories;

import com.informatingconfig.spring.app1.springboot_applications.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}