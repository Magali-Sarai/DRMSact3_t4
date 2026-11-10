package com.informatingconfig.spring.app1.springboot_applications.Models.dto;

public class ProductoDTO {

    private Integer id;
    private String nombre;
    private String categoria;
    private Double precio;

    public ProductoDTO() {
    }

    public ProductoDTO(Integer id, String nombre, String categoria, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
