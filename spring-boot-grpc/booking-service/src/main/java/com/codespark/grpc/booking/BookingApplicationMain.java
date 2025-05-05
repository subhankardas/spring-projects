package com.codespark.grpc.booking;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.codespark.grpc.booking.model.Booking;
import com.codespark.grpc.booking.service.BookingService;

import lombok.AllArgsConstructor;

@SpringBootApplication
@AllArgsConstructor
public class BookingApplicationMain implements CommandLineRunner {

	private final BookingService bookingService;

	public static void main(String[] args) {
		SpringApplication.run(BookingApplicationMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		bookingService.bookMovie(1);
		bookingService.bookMovie(4);

		bookingService.suggestMoviesForBooking("Adventure");
		bookingService.suggestMoviesForBooking("Unknown");

		bookingService.saveBookings(List.of(
				new Booking(5, "Movie 1", 5),
				new Booking(6, "Movie 2", 3),
				new Booking(7, "Movie 3", 4)));

		bookingService.getRatings(List.of(1, 3, 7));
	}

}
