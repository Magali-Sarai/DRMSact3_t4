package com.informatingconfig.spring.app1.springboot_applications.controllers;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.informatingconfig.spring.app1.springboot_applications.Models.Empleados;


@Controller  //Controlador
public class EjemploController {

    @GetMapping("/detalles_info")
    
    public String info(Model model){ //Modelo //DTo porque estamos viendo el nombre del emepleado y sus datos
        Empleados empleado1 = new Empleados("Juan", "Rodriguez", "Calle 1", "Gerente",
                                            35, 123456789, 001);
                                            
        model.addAttribute("Empleado", empleado1);
        return "detalles_info"; //vista
    }

    @ModelAttribute("Empleados")
    public List<Empleados> ListaEmpleados(){
        return Arrays.asList(
            new Empleados("Maria", "Perez", "Calle segunda", "Secretaria", 20, 123456789, 02),
            new Empleados("Maria2", "Perez", "Calle segunda", "Secretaria", 20, 123456789, 02),
            new Empleados("Maria3", "Perez", "Calle segunda", "Secretaria", 20, 123456789, 02),
            new Empleados("Maria4", "Perez", "Calle segunda", "Secretaria", 20, 123456789, 02),
            new Empleados("Maria5", "Perez", "Calle segunda", "Secretaria", 20, 123456789, 02)
        );

    }

}
