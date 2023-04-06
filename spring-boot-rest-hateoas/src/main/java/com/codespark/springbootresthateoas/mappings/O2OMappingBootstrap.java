package com.codespark.springbootresthateoas.mappings;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springbootresthateoas.mappings.one_to_one_bi.Company;
import com.codespark.springbootresthateoas.mappings.one_to_one_bi.CompanyRepository;
import com.codespark.springbootresthateoas.mappings.one_to_one_bi.Registration;
import com.codespark.springbootresthateoas.mappings.one_to_one_bi.RegistrationRepository;
import com.codespark.springbootresthateoas.mappings.one_to_one_uni.Profile;
import com.codespark.springbootresthateoas.mappings.one_to_one_uni.User;
import com.codespark.springbootresthateoas.mappings.one_to_one_uni.UserRepository;

@Component
public class O2OMappingBootstrap implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(O2OMappingBootstrap.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public void run(String... args) throws Exception {
        oneToOneUnidirectional();
        oneToOneBidirectional();
    }

    public void oneToOneUnidirectional() {
        User user = new User();
        user.setId(1);
        user.setName("Juan");
        user.setEmail("mail@me.com");

        Profile profile = new Profile();
        profile.setId(1);
        profile.setAddress("123 Main Street");
        profile.setDateOfBirth(LocalDate.of(1993, 3, 18));

        // One-to-One uni-directional mapping
        // User --> Profile
        user.setProfile(profile);
        userRepository.save(user);

        user = userRepository.findById(1).get();
        log.info("User: {}", user);
    }

    public void oneToOneBidirectional() {
        Company company = new Company();
        company.setId(1);
        company.setName("CodeSpark Tech");
        company.setEmail("mail@me.com");

        Registration registration = new Registration();
        registration.setId(1);
        registration.setReference("CDSPRK123");
        registration.setDate(LocalDate.of(2020, 3, 18));

        // One-to-One bidirectional mapping
        // Company <--> Registration
        company.setRegistration(registration);
        companyRepository.save(company);

        company = companyRepository.findById(1).get();
        registration = registrationRepository.findById(1).get();
        log.info("Company: {}", registration.getCompany());
        log.info("Registration: {}", company.getRegistration());
    }

}
