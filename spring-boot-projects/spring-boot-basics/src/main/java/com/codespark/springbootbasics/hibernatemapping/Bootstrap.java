package com.codespark.springbootbasics.hibernatemapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.codespark.springbootbasics.hibernatemapping.bootstrap.ManyToManyBootstrap;
import com.codespark.springbootbasics.hibernatemapping.bootstrap.OneToManyBootstrap;
import com.codespark.springbootbasics.hibernatemapping.bootstrap.OneToOneBootstrap;

@Component
public class Bootstrap implements CommandLineRunner {

	@Autowired
	OneToOneBootstrap oneToOneBootstrap;

	@Autowired
	OneToManyBootstrap oneToManyBootstrap;

	@Autowired
	ManyToManyBootstrap manyToManyBootstrap;

	@Override
	public void run(String... args) throws Exception {
		/**
		 * Create a blank MySQL database 'hibernate_mapping_db', uncomment the following
		 * lines and run the application.
		 */
//		oneToOneBootstrap.run();
//		oneToManyBootstrap.run();
//		manyToManyBootstrap.run();
	}
}