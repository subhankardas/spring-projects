package com.codespark.basicsecurity.bootstrap;

import java.util.ArrayList;
import java.util.List;

import com.codespark.basicsecurity.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.basicsecurity.entity.User;

@Component
public class UserDataBootstrap implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		List<String> roles1 = new ArrayList<>();
		roles1.add("ROLE_USER");

		List<String> roles2 = new ArrayList<>();
		roles2.add("ROLE_USER");
		roles2.add("ROLE_ADMIN");

		userRepository.save(new User("user", "pass", true, roles1));
		userRepository.save(new User("admin", "pass", true, roles2));
	}

}
