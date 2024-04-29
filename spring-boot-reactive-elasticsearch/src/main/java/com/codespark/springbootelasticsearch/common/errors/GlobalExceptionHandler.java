package com.codespark.springbootelasticsearch.common.errors;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * Global exception handler for writing error responses conditionally.
 */
@Slf4j
@Configuration
public class GlobalExceptionHandler implements ErrorWebExceptionHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
        log.error("Exception occurred {} of type {}", ex.getMessage(), ex.getClass().getName());

        // Set the status code based on the exception type
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        } else if (ex instanceof NullValueException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof NoSuchIndexException) {
            status = HttpStatus.NOT_FOUND;
        }

        // Write status code to the response
        exchange.getResponse().setStatusCode(status);

        // Create the error response body
        byte[] response = new byte[0];
        DataBuffer dataBuffer = null;
        try {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
            response = objectMapper.writeValueAsBytes(errorResponse);
        } catch (JsonProcessingException e) {
            log.error("Exception occurred while writing error response. {}", e.getMessage());
        } finally {
            dataBuffer = bufferFactory.wrap(response);
        }

        // Write the error response to the response
        if (response.length > 0) {
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return exchange.getResponse().writeWith(Mono.just(dataBuffer));
        }

        return exchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

}