package com.informatingconfig.spring.app1.springboot_applications.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informatingconfig.spring.app1.springboot_applications.Models.Empleados;
import com.informatingconfig.spring.app1.springboot_applications.Models.dto.ClaseDTO;


@RestController
@RequestMapping("/api")
public class EjemploRestController {

    @GetMapping("/detalles_info2")
    
    public ClaseDTO detalles_info(){
        //Empleados empleado1 = new Empleados("Juan", "Rodriguez", "Calle 1 No 2", "Gerente",
        //                                    35, 123456789, 001);
        //Map<String, Object> respuesta = new HashMap<>();
        //respuesta.put("Empleado",empleado1);
        //return respuesta;

        ClaseDTO usuario1 = new ClaseDTO();
        usuario1.setTitulo("Administrador");
        usuario1.setUsuario("Informaticonfig");
        return usuario1;

    }

}
