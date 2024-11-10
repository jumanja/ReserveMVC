package com.jm.ReserveMVC.repository;

import com.jm.ReserveMVC.entity.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReserveRepository extends JpaRepository<Reserve, Long> {
}
