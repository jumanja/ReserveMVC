package com.jm.ReserveMVC.service.impl;

import com.jm.ReserveMVC.dto.ReserveDTO;
import com.jm.ReserveMVC.entity.Reserve;
import com.jm.ReserveMVC.entity.Product;
import com.jm.ReserveMVC.entity.Client;
import com.jm.ReserveMVC.exception.ResourceNotFoundException;
import com.jm.ReserveMVC.repository.IReserveRepository;
import com.jm.ReserveMVC.service.IReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
public class ReserveService implements IReserveService {

    private IReserveRepository reserveRepository;

    @Autowired
    public ReserveService(IReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    @Override
    public ReserveDTO save(ReserveDTO reserveDTO) {
        //mapear nuestras entidades como DTO a mano
        //instanciar una entidad de turno
        Reserve reserveEntity = new Reserve();

        //instanciar un paciente
        Client clientEntity = new Client();
        clientEntity.setId(reserveDTO.getClient_id());

        //instanciar un producto
        Product productEntity = new Product();
        productEntity.setId(reserveDTO.getProduct_id());

        //seteamos el paciente y el producto a nuestra entidad turno
        reserveEntity.setClient(clientEntity);
        reserveEntity.setProduct(productEntity);

        //convertir el string del turnoDto que es la fecha a LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(reserveDTO.getDate(), formatter);

        //setear la fecha
        reserveEntity.setDate(date);

        //persistir en la BD
        reserveRepository.save(reserveEntity);

        //vamos a trabajar con el DTO que debemos devolver
        //generar una instancia de turno DTO
        ReserveDTO reserveDTOToReturn = new ReserveDTO();

        //le seteamos los datos de la entidad que persistimos
        reserveDTOToReturn.setId(reserveEntity.getId());
        reserveDTOToReturn.setDate(reserveEntity.getDate().toString());
        reserveDTOToReturn.setProduct_id(reserveEntity.getProduct().getId());
        reserveDTOToReturn.setClient_id(reserveEntity.getClient().getId());

        return reserveDTOToReturn;
    }

    @Override
    public Optional<ReserveDTO> findById(Long id) throws ResourceNotFoundException {
        //vamos a buscar la entidad por id a la BD
        Optional<Reserve> reserveToLookFor = reserveRepository.findById(id);

        //instanciamos el dto
        Optional<ReserveDTO> reserveDTO = null;

        if (reserveToLookFor.isPresent()) {
            //recuperar la entidad que se encontró y guardarla en la variable appointmen
            Reserve reserve = reserveToLookFor.get();

            //trabajar sobre la información que tenemos que devolver: dto
            //vamos a crear una intancia de turno dto para devolver
            ReserveDTO reserveDTOToReturn = new ReserveDTO();
            reserveDTOToReturn.setId(reserve.getId());
            reserveDTOToReturn.setClient_id(reserve.getClient().getId());
            reserveDTOToReturn.setProduct_id(reserve.getProduct().getId());
            reserveDTOToReturn.setDate(reserve.getDate().toString());

            reserveDTO = Optional.of(reserveDTOToReturn);
            return reserveDTO;

        } else {
            throw new ResourceNotFoundException("No se encontró el turno con id: " + id);
        }




    }

    @Override
    public ReserveDTO update(ReserveDTO reserveDTO) throws Exception {

        //chequeo que el turno a actualizar exista
        if(reserveRepository.findById(reserveDTO.getId()).isPresent()) {

            //buscar la entidad en la BD
            Optional<Reserve> reserveEntity = reserveRepository.findById(reserveDTO.getId());

            //instanciar un paciente
            Client clientEntity = new Client();
            clientEntity.setId(reserveDTO.getClient_id());

            //instanciar un producto
            Product productEntity = new Product();
            productEntity.setId(reserveDTO.getProduct_id());

            //seteamos el paciente y el producto a nuestra entidad turno
            reserveEntity.get().setClient(clientEntity);
            reserveEntity.get().setProduct(productEntity);

            //convertir el string del turnoDto que es la fecha a LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(reserveDTO.getDate(), formatter);

            //setear la fecha
            reserveEntity.get().setDate(date);

            //persistir en la BD
            reserveRepository.save(reserveEntity.get());

            //vamos a trabajar sobre la respuesta (dto) a devolver
            ReserveDTO reserveDTOToReturn = new ReserveDTO();
            reserveDTOToReturn.setId(reserveEntity.get().getId());
            reserveDTOToReturn.setClient_id(reserveEntity.get().getClient().getId());
            reserveDTOToReturn.setProduct_id(reserveEntity.get().getProduct().getId());
            reserveDTOToReturn.setDate(reserveEntity.get().getDate().toString());

            return reserveDTOToReturn;
        } else {
            throw new Exception("No se pudo actualizar el turno");
        }

    }

    @Override
    public Optional<ReserveDTO> delete(Long id) throws ResourceNotFoundException {
        //vamos a buscar la entidad por id en la BD y guardara en un Optional
        Optional<Reserve> reserveToLookFor = reserveRepository.findById(id);

        Optional<ReserveDTO> reserveDTO;
        if (reserveToLookFor.isPresent()) {
            //recuperar el turno que se encontró y lo vamos a guardar en una variable turno
            Reserve reserve =  reserveToLookFor.get();

            //vamos a devolver un dto
            //vamos a trabajar sobre ese dto a devolver
            //crear una instancia de ese dto
            ReserveDTO reserveDTOToReturn = new ReserveDTO();
            reserveDTOToReturn.setId(reserve.getId());
            reserveDTOToReturn.setProduct_id(reserve.getProduct().getId());
            reserveDTOToReturn.setClient_id(reserve.getClient().getId());
            reserveDTOToReturn.setDate(reserve.getDate().toString());

            reserveDTO = Optional.of(reserveDTOToReturn);
            return reserveDTO;

        } else {
            //vamos a lanzar la exception
            throw new ResourceNotFoundException("No se encontró el turno con id: " + id);
        }

    }

    @Override
    public List<ReserveDTO> findAll() {
        //vamos a traernos las entidades de la BD y la vamos a guardar en una lista
        List<Reserve> reserves = reserveRepository.findAll();

        //vamos a crear una lista vacía de turnos DTO
        List<ReserveDTO> reserveDTOS = new ArrayList<>();

        //recorremos la lista de entidades de turno para luego
        //guardarlas en la lista de turnos DTO
        for (Reserve reserve: reserves) {
            reserveDTOS.add(new ReserveDTO(reserve.getId(),
                    reserve.getClient().getId(), reserve.getProduct().getId(),
                    reserve.getDate().toString()));
        }

        return reserveDTOS;
    }
}
