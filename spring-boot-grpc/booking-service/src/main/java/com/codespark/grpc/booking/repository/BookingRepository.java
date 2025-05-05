package com.codespark.grpc.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codespark.grpc.booking.model.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
