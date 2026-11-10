package com.informatingconfig.spring.app1.springboot_applications.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/api/parametros")
public class RequesyParamControllers {

    @GetMapping("/detalle")
    public ParametroDTO detalle(@RequestParam(required = false, defaultValue = "tonotos") String informacion ){
        ParametroDTO parametro1 = new ParametroDTO();
        parametro1.setInformacion(informacion);
        return parametro1;
    }
    

}
