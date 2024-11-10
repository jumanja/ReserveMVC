package com.jm.ReserveMVC.service;

import com.jm.ReserveMVC.dto.ReserveDTO;
import com.jm.ReserveMVC.entity.Reserve;
import com.jm.ReserveMVC.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IReserveService {
    ReserveDTO save (ReserveDTO reserveDTO);
    Optional<ReserveDTO> findById(Long id) throws ResourceNotFoundException;
    ReserveDTO update(ReserveDTO reserve) throws Exception;
    Optional<ReserveDTO> delete(Long id) throws ResourceNotFoundException;
    List<ReserveDTO> findAll();
}
