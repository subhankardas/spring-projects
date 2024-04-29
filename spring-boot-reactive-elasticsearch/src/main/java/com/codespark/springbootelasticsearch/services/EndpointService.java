package com.codespark.springbootelasticsearch.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.codespark.springbootelasticsearch.common.errors.NullValueException;
import com.codespark.springbootelasticsearch.data.EndpointCustomRepository;
import com.codespark.springbootelasticsearch.models.Endpoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class EndpointService {

    private final EndpointCustomRepository endpointCustomRepository;

    public Mono<Endpoint> saveEndpoint(UUID machineId, Endpoint endpoint) {
        if (machineId == null) {
            log.error("Machine ID cannot be null.");
            throw new NullValueException("MachineIdNotFoundException");
        } else if (endpoint == null) {
            log.error("Endpoint data cannot be null.");
            throw new NullValueException("EndpointDataNotFoundException");
        }

        log.info("Saving endpoint: {}", endpoint);
        endpoint.setMachineId(machineId);
        return endpointCustomRepository.save(endpoint);
    }

    public Mono<Endpoint> getEndpoint(UUID machineId) {
        return endpointCustomRepository.findById(machineId);
    }

    public Flux<Endpoint> getEndpoints() {
        return endpointCustomRepository.findAll();
    }

}
