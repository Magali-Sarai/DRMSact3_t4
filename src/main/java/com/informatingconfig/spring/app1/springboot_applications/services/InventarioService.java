package com.informatingconfig.spring.app1.springboot_applications.services;

import com.informatingconfig.spring.app1.springboot_applications.Models.Categoria;
import com.informatingconfig.spring.app1.springboot_applications.Models.Producto;
import com.informatingconfig.spring.app1.springboot_applications.repositories.CategoriaRepository;
import com.informatingconfig.spring.app1.springboot_applications.repositories.ProductoRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Semilla inicial automática: si la BD está vacía, crea categorías y productos reales en MySQL
    @PostConstruct
    public void inicializarDatosBase() {
        if (categoriaRepository.count() == 0) {
            Categoria catAudio = categoriaRepository.save(new Categoria("Audio", "Micrófonos, interfaces y monitores"));
            Categoria catHardware = categoriaRepository.save(new Categoria("Hardware", "Microcontroladores, pantallas y sensores"));
            Categoria catRedes = categoriaRepository.save(new Categoria("Redes", "Routers, switches y puntos de acceso"));
            Categoria catSoftware = categoriaRepository.save(new Categoria("Software", "Licencias e IDEs de desarrollo"));

            productoRepository.save(new Producto("Micrófono USB Blue Yeti", 2450.00, 5, catAudio));
            productoRepository.save(new Producto("Placa ESP32 WiFi + Bluetooth", 185.50, 15, catHardware));
            productoRepository.save(new Producto("Router MikroTik hEX RB750Gr3", 1390.00, 8, catRedes));
        }
    }

    // --- Operaciones CRUD de Categorías ---
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id);
    }

    public Categoria guardarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // --- Operaciones CRUD de Productos (Entidad Principal) ---
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarProductoPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}
