package com.codespark.springbootbasics.fileshandle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FileHandleConfig implements CommandLineRunner {

	@Autowired
	private FileStorageService fileStorage;

	@Override
	public void run(String... args) throws Exception {
		/**
		 * Initial file storage setup i.e delete all files in storage and initialize
		 * storage freshly.
		 */
		fileStorage.deleteAll();
		fileStorage.init();
	}

}
