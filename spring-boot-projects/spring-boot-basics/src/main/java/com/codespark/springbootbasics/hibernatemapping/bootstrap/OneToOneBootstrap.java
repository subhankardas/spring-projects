package com.codespark.springbootbasics.hibernatemapping.bootstrap;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_bi.Company;
import com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_bi.RegistrationDetails;
import com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_uni.Profile;
import com.codespark.springbootbasics.hibernatemapping.entity.one_to_one_uni.User;
import com.codespark.springbootbasics.hibernatemapping.repository.CompanyRepository;
import com.codespark.springbootbasics.hibernatemapping.repository.RegistrationRepository;
import com.codespark.springbootbasics.hibernatemapping.repository.UserRepository;

@Component
public class OneToOneBootstrap {

	private static Logger logger = LoggerFactory.getLogger(OneToOneBootstrap.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	CompanyRepository companyRepository;

	@Autowired
	RegistrationRepository registrationRepository;

	public void run() {
		saveOneToOneUnidirectional();
		getOneToOneUnidirectional();

		saveOneToOneBidirectional();
		getOneToOneBidirectional();
	}

	public void saveOneToOneUnidirectional() {
		Profile profile = new Profile(0, "EMP", new Date());
		User user = new User(0, "Subhankar Das", "test@gmail.com", profile);

		userRepository.save(user);
		logger.info("USER: " + user);
	}

	public void getOneToOneUnidirectional() {
		Optional<User> data = userRepository.findByEmail("test@gmail.com");

		// USER --> PROFILE
		if (data.isPresent()) {
			User user = data.get();
			logger.info("USER: " + user);
		}
	}

	public void saveOneToOneBidirectional() {
		RegistrationDetails registrationDetails = new RegistrationDetails();
		Company company = new Company();

		registrationDetails.setId(0);
		registrationDetails.setFileID("FILE123");
		registrationDetails.setCreationDate(new Date());
		registrationDetails.setCompany(company);

		company.setId(0);
		company.setName("ABC CORP.");
		company.setReference("XYZ1123");
		company.setRegistration(registrationDetails);

		companyRepository.save(company);

		logger.info("COMPANY: " + company);
		logger.info("REGISTRATION: " + registrationDetails);
	}

	public void getOneToOneBidirectional() {
		Optional<Company> data1 = companyRepository.findById(1);

		// COMPANY <--> REGISTRATION
		if (data1.isPresent()) {
			Company company = data1.get();

			logger.info("COMPANY: " + company);
			logger.info("REGISTRATION: " + company.getRegistration());
		}

		Optional<RegistrationDetails> data2 = registrationRepository.findById(1);

		// REGISTRATION <--> COMPANY
		if (data2.isPresent()) {
			RegistrationDetails registrationDetails = data2.get();

			logger.info("REGISTRATION: " + registrationDetails);
			logger.info("COMPANY: " + registrationDetails.getCompany());
		}

	}

}