package com.informatingconfig.spring.app1.springboot_applications.controllers;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstudianteController {

    // Endpoint b)
    @GetMapping("/mis-datos")
    public Map<String, Object> obtenerDatosEstudiante() {
        Map<String, Object> datos = new LinkedHashMap<>();
        datos.put("nombreCompleto", "Magali Sarai Diego Revilla");
        datos.put("institucion", "Instituto Tecnológico de Oaxaca");
        datos.put("carrera", "Ingeniería en Sistemas Computacionales");
        datos.put("materiaActual", "Programación Web");

        List<String> materiasCursando = Arrays.asList(
            "Programación Web",
            "Gestion de Proyectos",
            "Administración de Redes",
            "Sistemas Programables",
            "PLF",
            "Software para la toma de decisiones"
        );
        datos.put("materias", materiasCursando);

        return datos;
    }
}