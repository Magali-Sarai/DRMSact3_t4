package com.informatingconfig.spring.app1.springboot_applications.controllers;

import com.informatingconfig.spring.app1.springboot_applications.Models.Categoria;
import com.informatingconfig.spring.app1.springboot_applications.Models.Producto;
import com.informatingconfig.spring.app1.springboot_applications.services.InventarioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/crud/productos")
public class InventarioRestController {

    private final InventarioService inventarioService;

    public InventarioRestController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    //GET: Listar todos los productos con su objeto Categoria completo
    @GetMapping
    public List<Producto> listarRest() {
        return inventarioService.listarProductos();
    }

    //POST: Crear producto indicando el categoriaId
    @PostMapping
    public Producto crearRest(@RequestBody Producto producto, @RequestParam Integer categoriaId) {
        Categoria cat = inventarioService.buscarCategoriaPorId(categoriaId).orElseThrow();
        producto.setCategoria(cat);
        return inventarioService.guardarProducto(producto);
    }

    // UPDATE (PUT): Actualizar un producto por su ID
    @PutMapping("/{id}")
    public Producto actualizarRest(@PathVariable Integer id, @RequestBody Producto datos, @RequestParam Integer categoriaId) {
        Producto existente = inventarioService.buscarProductoPorId(id).orElseThrow();
        Categoria cat = inventarioService.buscarCategoriaPorId(categoriaId).orElseThrow();
        existente.setNombre(datos.getNombre());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        existente.setCategoria(cat);
        return inventarioService.guardarProducto(existente);
    }

    // DELETE: Eliminar producto por su ID
    @DeleteMapping("/{id}")
    public Map<String, Object> eliminarRest(@PathVariable Integer id) {
        inventarioService.eliminarProducto(id);
        Map<String, Object> resp = new HashMap<>();
        resp.put("mensaje", "Producto con ID " + id + " eliminado correctamente de MySQL");
        resp.put("eliminado", true);
        return resp;
    }
}