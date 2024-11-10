package com.jm.ReserveMVC.controller;

import com.jm.ReserveMVC.dto.ReserveDTO;
import com.jm.ReserveMVC.entity.Reserve;
import com.jm.ReserveMVC.exception.ResourceNotFoundException;
import com.jm.ReserveMVC.service.IReserveService;
import com.jm.ReserveMVC.service.IProductService;
import com.jm.ReserveMVC.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class ReserveController {

    private IReserveService reserveService;

    private IProductService productService;

    private IClientService clientService;

    @Autowired
    public ReserveController(IReserveService reserveService, IProductService productService, IClientService clientService) {
        this.reserveService = reserveService;
        this.productService = productService;
        this.clientService = clientService;
    }

    //este endpoint consulta todos los turnos
    @GetMapping
    public ResponseEntity<List<ReserveDTO>> findAll() {
        return ResponseEntity.ok(reserveService.findAll());
    }

    @PostMapping
    public ResponseEntity<ReserveDTO> save(@RequestBody ReserveDTO reserveDTO) {
        ResponseEntity<ReserveDTO> response;

        //chequeamos que existan el producto y el paciente
        if (productService.findById(reserveDTO.getProduct_id()).isPresent()
                && clientService.findById(reserveDTO.getClient_id()).isPresent()) {
            //seteamos al ResponseEntity con el código 200 y le agregamos el turno como cuerpo de la respuesta
            response = ResponseEntity.ok(reserveService.save(reserveDTO));

        } else {
            //setear al ResponseEntity el código 400
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReserveDTO> findById(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<ReserveDTO> reserveToLookFor = reserveService.findById(id);

        if(reserveToLookFor.isPresent()) {
            return ResponseEntity.ok(reserveToLookFor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<ReserveDTO> update(@RequestBody ReserveDTO reserveDTO) throws Exception {
        ResponseEntity<ReserveDTO> response;

        //chequeamos que existan el producto y el paciente
        if (productService.findById(reserveDTO.getProduct_id()).isPresent()
                && clientService.findById(reserveDTO.getClient_id()).isPresent()) {
            //ambos existen en la DB
            //seteamos al ResponseEntity con el código 200 y le agregamos el turno dto como cuerpo de la respuesta
            response = ResponseEntity.ok(reserveService.update(reserveDTO));

        } else {
            //uno no existe, entonces bloqueamos la operación
            //setear al ResponseEntity el código 400
            response = ResponseEntity.badRequest().build();
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ResourceNotFoundException {
        reserveService.delete(id);
        return ResponseEntity.ok("Se eliminó el turno con id: " + id);
    }


}
