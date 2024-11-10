package com.jm.ReserveMVC.service.impl;

import com.jm.ReserveMVC.entity.Client;
import com.jm.ReserveMVC.exception.ResourceNotFoundException;
import com.jm.ReserveMVC.repository.IClientRepository;
import com.jm.ReserveMVC.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {
    private IClientRepository clientRepository;

    @Autowired
    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public void update(Client client) {
        clientRepository.save(client);
    }

    @Override
    public void delete(Long id) throws ResourceNotFoundException {
        Optional<Client> clientToLookFor = findById(id);
        if (clientToLookFor.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se puedo eliminar el paciente con id: " + id);
        }
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }
}
