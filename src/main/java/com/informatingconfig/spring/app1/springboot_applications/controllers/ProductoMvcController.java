package com.informatingconfig.spring.app1.springboot_applications.controllers;

import com.informatingconfig.spring.app1.springboot_applications.Models.dto.ProductoDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/productos")
public class ProductoMvcController {

    // @Value leyendo propiedades desde application.properties
    @Value("${config.tarea}")
    private String tarea;

    @Value("${config.nombre}")
    private String nombre;

    @Value("${config.materia}")
    private String materia;

    // Lista base usando ProductoDTO
    private List<ProductoDTO> listaProductos = new ArrayList<>(Arrays.asList(
        new ProductoDTO(1, "Microfono USB Blue Yeti", "Audio", 2450.00),
        new ProductoDTO(2, "Placa ESP32 WiFi + BT", "Hardware", 185.50),
        new ProductoDTO(3, "Display TFT ILI9341 SPI", "Hardware", 210.00),
        new ProductoDTO(4, "Router MikroTik hEX RB750Gr3", "Redes", 1390.00),
        new ProductoDTO(5, "Access Point TP-Link Omada", "Redes", 1850.00)
    ));

    // @ModelAttribute para disponibilizar los valores de @Value en las vistas
    @ModelAttribute("tarea")
    public String obtenerTarea() {
        return tarea;
    }

    @ModelAttribute("nombre")
    public String obtenerNombre() {
        return nombre;
    }

    @ModelAttribute("materia")
    public String obtenerMateria() {
        return materia;
    }

    // a) Vista que muestra la lista usando Model y th:each
    @GetMapping("/lista")
    public String mostrarLista(Model model) {
        model.addAttribute("titulo", "Productos");
        model.addAttribute("productos", listaProductos);
        return "catalogo_productos";
    }

    // Endpoint @RequestParam para filtrar la lista por categoría
    @GetMapping("/filtrar")
    public String filtrarPorCategoria(@RequestParam String categoria, Model model) {
        List<ProductoDTO> filtrados = listaProductos.stream()
                .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList());

        model.addAttribute("titulo", "Productos por: " + categoria);
        model.addAttribute("categoriaActual", categoria);
        model.addAttribute("productos", filtrados);
        return "catalogo_productos";
    }

    // Endpoint con @PathVariable para ver el detalle de un elemento por su id
    @GetMapping("/ver/{id}")
    public String verDetalleProducto(@PathVariable Integer id, Model model) {
        ProductoDTO encontrado = listaProductos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);

        model.addAttribute("titulo", "Detalle del Producto (@PathVariable ID: " + id + ")");
        model.addAttribute("producto", encontrado);
        model.addAttribute("idBuscado", id);
        return "detalle_producto";
    }

    // b) Vista con formulario que usa @ModelAttribute
    @GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Formulario de Registro (@ModelAttribute)");
        model.addAttribute("nuevoProducto", new ProductoDTO());
        return "formulario_producto";
    }

    // Recibe los datos del formulario con @ModelAttribute y los muestra
    @PostMapping("/guardar")
    public String procesarFormulario(@ModelAttribute("nuevoProducto") ProductoDTO productoCapturado, Model model) {
        if (productoCapturado.getId() == null) {
            productoCapturado.setId(listaProductos.size() + 1);
        }
        listaProductos.add(productoCapturado);

        model.addAttribute("titulo", "Producto @ModelAttribute");
        model.addAttribute("mensaje", "Producto registrado y agregado a la lista");
        model.addAttribute("producto", productoCapturado);
        return "detalle_producto";
    }
}
