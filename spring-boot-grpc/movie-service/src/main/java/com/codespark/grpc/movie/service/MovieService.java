package com.codespark.grpc.movie.service;

import java.util.List;
import java.util.Random;

import org.springframework.grpc.server.service.GrpcService;

import com.codespark.grpc.common.GetMovieRequest;
import com.codespark.grpc.common.GetMoviesByGenreRequest;
import com.codespark.grpc.common.MovieDetails;
import com.codespark.grpc.common.MovieRating;
import com.codespark.grpc.common.MovieServiceGrpc;
import com.codespark.grpc.common.SaveMoviesResponse;
import com.codespark.grpc.movie.model.Movie;
import com.codespark.grpc.movie.repository.MovieRepository;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@GrpcService
@AllArgsConstructor
@Slf4j
public class MovieService extends MovieServiceGrpc.MovieServiceImplBase {

    private final Random random = new Random();
    private final MovieRepository movieRepository;

    @Override
    public void getMovie(GetMovieRequest request, StreamObserver<MovieDetails> responseObserver) {
        // Get movie by ID
        movieRepository.findById(request.getId())
                .ifPresentOrElse(movie -> {
                    // Movie found, send response
                    log.info("Movie found: {}", movie);

                    responseObserver
                            .onNext(MovieDetails.newBuilder().setId(movie.getId()).setTitle(movie.getTitle())
                                    .setGenre(movie.getGenre()).setRelease(movie.getRelease()).build());
                    responseObserver.onCompleted();
                }, () -> {
                    // Movie not found, send error
                    log.error("Movie not found with ID: {}", request.getId());

                    responseObserver
                            .onError(Status.NOT_FOUND.withDescription("Movie not found").asRuntimeException());
                });
    }

    @Override
    public void getMoviesByGenre(GetMoviesByGenreRequest request, StreamObserver<MovieDetails> responseObserver) {
        // Get movies by genre
        List<Movie> movies = movieRepository.findByGenre(request.getGenre());

        // Check if no movies found
        if (movies.isEmpty()) {
            log.error("No movies found with genre: {}", request.getGenre());
            responseObserver
                    .onError(Status.NOT_FOUND.withDescription("No movies found with genre " + request.getGenre())
                            .asRuntimeException());
            return;
        }

        // Stream movies as response
        for (Movie movie : movies) {
            responseObserver.onNext(MovieDetails.newBuilder().setId(movie.getId()).setTitle(movie.getTitle())
                    .setGenre(movie.getGenre()).setRelease(movie.getRelease()).build());

            try { // Simulate processing delay
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

        // Complete stream
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<MovieDetails> saveMovies(StreamObserver<SaveMoviesResponse> responseObserver) {
        return new StreamObserver<MovieDetails>() {
            int count = 0;

            @Override
            public void onNext(MovieDetails movieDetails) {
                // Collect movie details from stream, save to database
                var movie = movieRepository.save(new Movie(movieDetails.getId(), movieDetails.getTitle(),
                        movieDetails.getGenre(), movieDetails.getRelease()));

                log.info("Movie saved: {}", movie);
                count++;
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(SaveMoviesResponse.newBuilder().setCount(count).build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<GetMovieRequest> getRatings(StreamObserver<MovieRating> responseObserver) {
        return new StreamObserver<GetMovieRequest>() {
            @Override
            public void onNext(GetMovieRequest getMovieRequest) {
                // Get movie rating by ID
                movieRepository.findById(getMovieRequest.getId())
                        .ifPresentOrElse(movie -> {
                            // Movie found, send response
                            log.info("Movie found: {}", movie);

                            responseObserver
                                    .onNext(MovieRating.newBuilder().setId(movie.getId()).setTitle(movie.getTitle())
                                            .setRating(random.nextFloat(10)).build());
                        }, () -> {
                            // Movie not found, send error
                            log.error("Movie not found with ID: {}", getMovieRequest.getId());

                            responseObserver
                                    .onError(Status.NOT_FOUND.withDescription("Movie not found").asRuntimeException());
                        });
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onError(throwable);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

}
