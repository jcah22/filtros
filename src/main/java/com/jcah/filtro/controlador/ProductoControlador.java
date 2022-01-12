package com.jcah.filtro.controlador;

import java.util.List;

import com.jcah.filtro.entidades.Producto;
import com.jcah.filtro.servicio.ProductoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ProductoControlador {

    @Autowired
    private  ProductoServicio servicio;

    @GetMapping("/")
    public String verPaginaInicio(Model model,@Param("palabraClave") String palabraClave){
        
        List<Producto> listaProductos = servicio.listAll(palabraClave);
      
        
    model.addAttribute("productosAll", listaProductos);
    model.addAttribute("palabraClave", palabraClave);
    return "index";

    }
    
    @GetMapping("/nuevo")
    public String registrarProducto(Model model){

        Producto p  =new Producto();
        model.addAttribute("producto", p);
        return "nuevo_producto";



    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto ){
        

        servicio.save(producto);

            return "redirect:/";

    }


    @GetMapping("/editar/{id}" )
    public ModelAndView editarProducto(@PathVariable(name = "id") Long id){

        ModelAndView modelo  = new ModelAndView("editar_producto");
        Producto producto = servicio.get(id);
        modelo.addObject("producto", producto);
        return modelo;

    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Long id){

        servicio.delete(id);

        return "redirect:/";
    }
    
    
    
}
