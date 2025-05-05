package com.codespark.grpc.booking.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.codespark.grpc.booking.model.Booking;
import com.codespark.grpc.booking.repository.BookingRepository;
import com.codespark.grpc.common.GetMovieRequest;
import com.codespark.grpc.common.GetMoviesByGenreRequest;
import com.codespark.grpc.common.MovieDetails;
import com.codespark.grpc.common.MovieRating;
import com.codespark.grpc.common.MovieServiceGrpc;
import com.codespark.grpc.common.SaveMoviesResponse;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;
    private final Random random = new Random();

    // gRPC stubs
    private final MovieServiceGrpc.MovieServiceBlockingStub blockingStub;
    private final MovieServiceGrpc.MovieServiceStub asyncStub;

    public void bookMovie(Integer movieId) {
        // Get movie using blocking stub (gRPC client)
        final var movieRequest = GetMovieRequest.newBuilder().setId(movieId).build();
        final var movie = blockingStub.getMovie(movieRequest);

        // Simulate booking
        var booking = bookingRepository.save(new Booking(movieId, movie.getTitle(), random.nextInt(10)));

        log.info("Booked movie: {}", booking);
    }

    public void suggestMoviesForBooking(String genre) {
        GetMoviesByGenreRequest request = GetMoviesByGenreRequest.newBuilder().setGenre(genre).build();
        asyncStub.getMoviesByGenre(request, new StreamObserver<>() {
            @Override
            public void onNext(MovieDetails movie) {
                log.info("Movie found: {} genre: {}", movie.getTitle(), movie.getGenre());
            }

            @Override
            public void onError(Throwable ex) {
                log.error("Error getting movies: {}", ex.getMessage());
            }

            @Override
            public void onCompleted() {
                // Stream completed, do nothing
            }
        });
    }

    public void saveBookings(List<Booking> bookings) {
        StreamObserver<SaveMoviesResponse> responseObserver = new StreamObserver<>() {
            @Override
            public void onNext(SaveMoviesResponse value) {
                log.info("Movies saved: {}", value.getCount());
            }

            @Override
            public void onError(Throwable ex) {
                log.error("Error saving movies: {}", ex.getMessage());
            }

            @Override
            public void onCompleted() {
                // Stream completed, do nothing
            }
        };

        StreamObserver<MovieDetails> requestObserver = asyncStub.saveMovies(responseObserver);

        // Send movie details to stream
        for (Booking booking : bookings) {
            requestObserver
                    .onNext(MovieDetails.newBuilder().setId(booking.getId()).setTitle(booking.getTitle()).build());
        }

        requestObserver.onCompleted();
    }

    public void getRatings(List<Integer> movieIds) {
        StreamObserver<GetMovieRequest> requestObserver = asyncStub.getRatings(new StreamObserver<>() {
            @Override
            public void onNext(MovieRating value) {
                log.info("Movie: {} rating: {}", value.getTitle(), value.getRating());
            }

            @Override
            public void onError(Throwable ex) {
                log.error("Error getting ratings: {}", ex.getMessage());
            }

            @Override
            public void onCompleted() {
                // Stream completed, do nothing
            }
        });

        // Send movie IDs to stream
        for (Integer movieId : movieIds) {
            requestObserver.onNext(GetMovieRequest.newBuilder().setId(movieId).build());
        }

        requestObserver.onCompleted();
    }
}
