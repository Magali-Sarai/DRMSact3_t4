package com.informatingconfig.spring.app1.springboot_applications.controllers;

import com.informatingconfig.spring.app1.springboot_applications.Models.Categoria;
import com.informatingconfig.spring.app1.springboot_applications.Models.Producto;
import com.informatingconfig.spring.app1.springboot_applications.services.InventarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/crud")
public class InventarioCrudController {

    private final InventarioService inventarioService;

    public InventarioCrudController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // LEERLista todos los productos mostrando el nombre de su Categoría relacionada
    @GetMapping({"", "/", "/productos"})
    public String listar(Model model) {
        model.addAttribute("productos", inventarioService.listarProductos());
        model.addAttribute("categorias", inventarioService.listarCategorias());
        return "crud_index";
    }

    // Mostrar formulario para CREAR un nuevo producto
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        Producto producto = new Producto();
        producto.setCategoria(new Categoria());
        model.addAttribute("tituloFormulario", "Registrar Nuevo Producto (CREATE)");
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", inventarioService.listarCategorias());
        return "crud_form";
    }

    //CREATE /UPDATE: Guarda el producto y su relación @ManyToOne en MySQL
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("producto") Producto producto,
                          @RequestParam("categoriaId") Integer categoriaId) {
        Categoria cat = inventarioService.buscarCategoriaPorId(categoriaId).orElse(null);
        producto.setCategoria(cat);
        inventarioService.guardarProducto(producto);
        return "redirect:/crud/productos";
    }

    //UPDATE: Carga los datos de un producto existente por su ID para editarlo
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Producto producto = inventarioService.buscarProductoPorId(id).orElse(null);
        if (producto == null) {
            return "redirect:/crud/productos";
        }
        model.addAttribute("tituloFormulario", "Editar Producto #" + id + " (UPDATE)");
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", inventarioService.listarCategorias());
        return "crud_form";
    }

    //DELETE: Elimina el producto de la base de datos MySQL por su ID
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        inventarioService.eliminarProducto(id);
        return "redirect:/crud/productos";
    }
}