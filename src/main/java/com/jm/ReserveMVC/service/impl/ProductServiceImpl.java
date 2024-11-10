package com.jm.ReserveMVC.service.impl;

import com.jm.ReserveMVC.entity.Product;
import com.jm.ReserveMVC.exception.ResourceNotFoundException;
import com.jm.ReserveMVC.repository.IProductRepository;
import com.jm.ReserveMVC.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private IProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void update(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        //vamos a buscar por id el producto
        //y si no existe vamos a lanzar la excepción

        //vamos a buscar primero al producto por id
        Optional<Product> productToLookFor = findById(id);

        if (productToLookFor.isPresent()) {
            productRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se pudo eliminar el producto con id: " + id);
        }


    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findByRegistration(Integer registration) {
        return productRepository.findByRegistration(registration);
    }
}
