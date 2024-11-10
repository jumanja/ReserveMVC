package com.jm.ReserveMVC.controller;

import com.jm.ReserveMVC.dto.ReserveDTO;
import com.jm.ReserveMVC.entity.Address;
import com.jm.ReserveMVC.entity.Product;
import com.jm.ReserveMVC.entity.Client;
import com.jm.ReserveMVC.service.IReserveService;
import com.jm.ReserveMVC.service.IProductService;
import com.jm.ReserveMVC.service.IClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ReserveControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IClientService clientService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IReserveService reserveService;

    @BeforeEach
    public void setUp() {
        dataLoad();
    }

    public void dataLoad() {
        Address address = new Address();
        address.setStreet("Calle 182");
        address.setNumber(735);
        address.setLocation("Usaquen");
        address.setProvince("Bogotá");

        Client client = new Client();
        client.setName("Julio");
        client.setLastName("Dominguez");
        client.setEmail("jdominguez@gmail.com");
        client.setCardIdentity(12345);
        client.setAdmissionOfDate(LocalDate.of(2022,1,29));
        client.setAddress(address);

        Product product = new Product();
        product.setName("Hotel");
        product.setLastName("Libre");
        product.setRegistration(547);

        clientService.save(client);
        productService.save(product);

        ReserveDTO reserveDTO = new ReserveDTO();
        reserveDTO.setDate("2024-12-11");
        reserveDTO.setClient_id(1L);
        reserveDTO.setProduct_id(1L);

        ReserveDTO  reserveSaved = reserveService.save(reserveDTO);
    }

    @Test
    public void ListAllReserveTest() throws Exception {
        MvcResult response = mockMvc.perform(MockMvcRequestBuilders
                .get("/reservas").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertFalse(response.getResponse().getContentAsString().isEmpty());
    }

}