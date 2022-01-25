package com.codespark.springbootbasics.fileshandle;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.codespark.springbootbasics.fileshandle.exception.StorageException;
import com.codespark.springbootbasics.fileshandle.exception.StorageFileNotFoundException;

public interface FileStorageService {

	/**
	 * Initialize local file storage.
	 * 
	 * @throws StorageException
	 */
	void init() throws StorageException;

	/**
	 * Receives a @MultipartFile file and stores it into local file storage
	 * location.
	 * 
	 * @param file File to store
	 * @throws StorageException
	 * @throws MultipartException
	 */
	void store(MultipartFile file) throws StorageException, MultipartException;

	/**
	 * Returns a list of stored files within the local file storage.
	 * 
	 * @return List of files
	 * @throws StorageException
	 */
	Stream<Path> loadAll() throws StorageException;

	/**
	 * Returns a given file as resource.
	 * 
	 * @param filename File name
	 * @return File resource
	 * @throws StorageFileNotFoundException
	 */
	Resource loadAsResource(String filename) throws StorageFileNotFoundException;

	/**
	 * Deletes all files within the local file storage.
	 */
	void deleteAll();

}