package com.codespark.reactiverestful;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import com.codespark.reactiverestful.models.Employee;
import com.codespark.reactiverestful.models.EmployeeDTO;
import com.codespark.reactiverestful.repository.EmployeeReactiveRepository;
import com.codespark.reactiverestful.repository.EmployeeRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

class EmployeeNotFoundException extends RuntimeException {
}

@AllArgsConstructor
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeReactiveRepository employeeReactiveRepository;

    public EmployeeDTO getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return new EmployeeDTO(employee.get().getName(), employee.get().getRole());
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    public CompletableFuture<EmployeeDTO> getEmployeeByIdAsync(Integer id) {
        CompletableFuture<EmployeeDTO> employeeFuture = new CompletableFuture<>();
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) { // Complete successfully
            employeeFuture = CompletableFuture
                    .completedFuture(new EmployeeDTO(employee.get().getName(), employee.get().getRole()));
        } else { // Complete with exception
            employeeFuture.completeExceptionally(new EmployeeNotFoundException()); // Throws CompletionException with
                                                                                   // custom exception as the cause
        }
        return employeeFuture;
    }

    public Mono<EmployeeDTO> getEmployeeByIdReactive(Integer id) {
        return employeeReactiveRepository.findById(id).map(emp -> new EmployeeDTO(emp.getName(), emp.getRole()));
    }

}
