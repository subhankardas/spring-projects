package com.codespark.reactiverestful;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codespark.reactiverestful.models.EmployeeDTO;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/sync/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(employeeService.getEmployeeById(id));
        } catch (EmployeeNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/async/{id}")
    public CompletableFuture<ResponseEntity<EmployeeDTO>> getEmployeeByIdAsync(@PathVariable Integer id) {
        return employeeService.getEmployeeByIdAsync(id).thenApply(emp -> {
            return ResponseEntity.ok(emp);
        }).exceptionally(ex -> {
            if (ex.getCause() instanceof EmployeeNotFoundException) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.internalServerError().build();
        });
    }

    @GetMapping("/reactive/{id}")
    public Mono<ResponseEntity<EmployeeDTO>> getEmployeeByIdReactive(@PathVariable Integer id) {
        return employeeService.getEmployeeByIdReactive(id).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

}
