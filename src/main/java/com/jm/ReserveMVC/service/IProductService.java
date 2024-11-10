package com.jm.ReserveMVC.service;

import com.jm.ReserveMVC.entity.Product;
import com.jm.ReserveMVC.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    Product save (Product product);
    Optional<Product> findById(Long id);
    void update(Product product);
    void delete(Long id) throws ResourceNotFoundException;
    List<Product> findAll();
    Optional<Product> findByRegistration(Integer registration);
}
