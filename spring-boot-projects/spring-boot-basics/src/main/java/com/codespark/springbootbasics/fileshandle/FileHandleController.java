package com.codespark.springbootbasics.fileshandle;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.codespark.springbootbasics.fileshandle.exception.StorageException;
import com.codespark.springbootbasics.fileshandle.exception.StorageFileNotFoundException;

@RestController
@RequestMapping("/files")
public class FileHandleController {

	@Autowired
	private FileStorageService fileStorage;

	@GetMapping
	public List<String> listUploadedFiles() throws StorageException {
		return fileStorage.loadAll().map(path -> path.getFileName().toString()).collect(Collectors.toList());
	}

	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) throws StorageFileNotFoundException {
		Resource file = fileStorage.loadAsResource(filename);

		/*
		 * Attach file resource to the response, user can download the file as
		 * attachment by visiting the URL from browser.
		 */
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@PostMapping
	public String handleFileUpload(@RequestParam("file") MultipartFile file)
			throws StorageException, MultipartException, SizeLimitExceededException {
		// Upload file into storage location
		fileStorage.store(file);

		return "File uploaded successfully.";
	}

	/**
	 * Exception handlers for various storage and file exceptions.
	 */
	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFoundException(StorageFileNotFoundException ex) {
		return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(StorageException.class)
	public ResponseEntity<?> handleStorageException(StorageException ex) {
		return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MultipartException.class)
	public ResponseEntity<?> handleMultipartException(MultipartException ex) {
		return new ResponseEntity<>("No valid file found with request.", HttpStatus.BAD_REQUEST);
	}

}
