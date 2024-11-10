package com.jm.ReserveMVC.repository;

import com.jm.ReserveMVC.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

    //HQL o JPQL
//    @Query("SELECT d FROM Product d WHERE d.registration=?1")
    Optional<Product> findByRegistration(Integer registration);

}
