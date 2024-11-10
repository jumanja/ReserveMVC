package com.jm.ReserveMVC.repository;

import com.jm.ReserveMVC.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long> {
}
