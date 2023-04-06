package com.codespark.springbootresthateoas.mappings;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springbootresthateoas.mappings.one_to_many_bi.Employee;
import com.codespark.springbootresthateoas.mappings.one_to_many_bi.EmployeeRepository;
import com.codespark.springbootresthateoas.mappings.one_to_many_bi.Manager;
import com.codespark.springbootresthateoas.mappings.one_to_many_bi.ManagerRepository;
import com.codespark.springbootresthateoas.mappings.one_to_many_uni.Asset;
import com.codespark.springbootresthateoas.mappings.one_to_many_uni.Broker;
import com.codespark.springbootresthateoas.mappings.one_to_many_uni.BrokerRepository;

@Component
public class O2MMappingBootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(O2MMappingBootstrap.class);

    @Autowired
    private BrokerRepository brokerRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        oneToManyUnidirectional();
        oneToManyBiDirectional();
    }

    private void oneToManyUnidirectional() {
        Broker broker = new Broker();
        broker.setId(1);
        broker.setName("John Broker");
        broker.setAddress("123 Main Street");

        List<Asset> assets = List.of(
                new Asset(1, "MFT", 12.3f),
                new Asset(2, "AAPL", 45.6f),
                new Asset(3, "GOOG", 78.9f));

        // One-to-Many uni-directional mapping
        // Broker --> Assets
        broker.setAssets(assets);
        brokerRepository.save(broker);

        broker = brokerRepository.findById(1).get();
        log.info("Broker: {}", broker);
    }

    private void oneToManyBiDirectional() {
        Manager manager = new Manager();
        manager.setId(1);
        manager.setName("John Manager");
        manager.setEmail("mngr@example.com");

        List<Employee> employees = List.of(
                new Employee(1, "John", "emp1@example.com", manager),
                new Employee(2, "Jane", "emp2@example.com", manager),
                new Employee(3, "Joe", "emp3@example.com", manager));

        // One-to-Many bi-directional mapping
        // Manager <--> Employees
        manager.setEmployees(employees);
        managerRepository.save(manager);

        manager = managerRepository.findById(1).get();
        Employee employee = employeeRepository.findById(1).get();
        log.info("Manager: {}", employee.getManager());
        log.info("Employees: {}", manager.getEmployees());
    }

}
