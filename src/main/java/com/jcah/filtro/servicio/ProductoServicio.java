package com.jcah.filtro.servicio;

import java.util.List;

import com.jcah.filtro.entidades.Producto;
import com.jcah.filtro.repositorio.ProductoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio {

    @Autowired
    private  ProductoRepositorio productoRepositorio;

    public List<Producto> listAll(String palabraClave ){
        if(palabraClave != null){
            return productoRepositorio.findAll(palabraClave);
        }

        return productoRepositorio.findAll();
        
    }

    public void save(Producto producto){
        productoRepositorio.save(producto);
    }

    public Producto get(Long id){
        return productoRepositorio.getById(id);
    }

    public void delete(Long id){
        productoRepositorio.deleteById(id);
    }
    
}
