package com.jm.ReserveMVC.controller;

import com.jm.ReserveMVC.entity.Client;
import com.jm.ReserveMVC.exception.ResourceNotFoundException;
import com.jm.ReserveMVC.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class ClientController {

    private IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    //un endpoint que nos permita agregar un paciente
    @PostMapping
    public Client save(@RequestBody Client client) {
        return clientService.save(client);
    }

    //un endpoint que nos permita actualizar un paciente que ya esté registrado
    @PutMapping
    public void update(@RequestBody Client client) {
        clientService.update(client);
    }

    @GetMapping
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        clientService.delete(id);
        return ResponseEntity.ok("Se eliminó el paciente con id: " + id);
    }

}
