package com.jm.ReserveMVC.controller;

import com.jm.ReserveMVC.entity.Product;
import com.jm.ReserveMVC.exception.ResourceNotFoundException;
import com.jm.ReserveMVC.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductController {

    private IProductService iProductService;

    @Autowired
    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    //localhost:8080/product/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> product = iProductService.findById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //vamos a guardar un nuevo producto en la BD
    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(iProductService.save(product));
    }

    //vamos a actualizar los datos de un producto
    @PutMapping
    public ResponseEntity<String> update(@RequestBody Product product) {
        ResponseEntity<String> response;
        Optional<Product> productToLookFor = iProductService.findById(product.getId());

        if (productToLookFor.isPresent()) {
            iProductService.update(product);
            response = ResponseEntity.ok("Se actualizó el producto con nombre: " + product.getName());

        } else {
            response = ResponseEntity.ok().body("No se puede actualizar un producto que no existe en la base de datos");
        }
        return response;
    }

    //vamos a borrar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        iProductService.delete(id);
        return ResponseEntity.ok("Se eliminó el producto co id: " + id);
    }

    //vamos a listar todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(iProductService.findAll());
    }

    @GetMapping("/registration/{registration}")
    public ResponseEntity<Product> findByRegistration(@PathVariable Integer registration) throws Exception {
        Optional<Product> product = iProductService.findByRegistration(registration);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            throw new Exception("no se encontró la matrícula: " + registration);
        }
    }
}
