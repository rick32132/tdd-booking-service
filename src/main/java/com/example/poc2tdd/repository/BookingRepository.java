package com.example.poc2tdd.repository;

import com.example.poc2tdd.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, String> {

    Optional<Booking> findByReserveName(String name);
}
