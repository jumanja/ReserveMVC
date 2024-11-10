package com.jm.ReserveMVC.service;

import com.jm.ReserveMVC.entity.Client;
import com.jm.ReserveMVC.service.impl.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void findById() {
        Long idClient = 3L;

        //buscar al paciente
        Optional< Client> client = clientService.findById(idClient);
        assertNotNull(client);
    }
}