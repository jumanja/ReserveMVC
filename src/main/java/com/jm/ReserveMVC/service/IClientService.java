package com.jm.ReserveMVC.service;

import com.jm.ReserveMVC.entity.Client;
import com.jm.ReserveMVC.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    Client save (Client client);
    Optional<Client> findById(Long id);
    void update(Client client);
    void delete(Long id) throws ResourceNotFoundException;
    List<Client> findAll();
}
