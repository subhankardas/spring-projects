package com.codespark.grpc.booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codespark.grpc.common.MovieServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Configuration
public class MovieServiceStub {

    @Bean
    ManagedChannel movieServiceChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 9090).usePlaintext().build();
    }

    @Bean
    MovieServiceGrpc.MovieServiceBlockingStub blockingStub(ManagedChannel movieServiceChannel) {
        return MovieServiceGrpc.newBlockingStub(movieServiceChannel);
    }

    @Bean
    MovieServiceGrpc.MovieServiceStub asyncStub(ManagedChannel movieServiceChannel) {
        return MovieServiceGrpc.newStub(movieServiceChannel);
    }

}
