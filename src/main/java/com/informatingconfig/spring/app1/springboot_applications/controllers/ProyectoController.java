package com.informatingconfig.spring.app1.springboot_applications.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatingconfig.spring.app1.springboot_applications.Models.dto.ProductoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProyectoController {

    @GetMapping("/identificador")
    public String obtenerIdentificador() {
        return "Proyecto: DRMSact1_t4 (Diego Revilla Magali Sarai";
    }

    // Endpoint c) 
    @GetMapping("/estado-servidor")
    public Map<String, Object> obtenerEstado() {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("codigoHttp", 200);
        respuesta.put("servidorActivo", true);
        respuesta.put("javaVersion", 21);
        respuesta.put("tema", 4);
        respuesta.put("actividad", 1);
        respuesta.put("mensaje", "corriendo sin errores:D");
        return respuesta;
    }



    // Endpoint POST REST que recibe ProductoDTO en JSON desde Postman y regresa JSON
    @PostMapping("/api/productos/crear")
    public Map<String, Object> crearProductoPostman(@RequestBody ProductoDTO productoDto) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", "201 CREATED");
        respuesta.put("mensaje", "ProductoDTO recibido exitosamente por POST (REST JSON)");
        respuesta.put("autora", "Magali Sarai Diego Revilla");
        respuesta.put("productoRecibido", productoDto);
        return respuesta;
    }
}