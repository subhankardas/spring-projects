package com.codespark.springbootfilebatch.filehandling;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    void init() throws StorageException;

    void store(MultipartFile file) throws StorageException, MultipartException;

    Stream<Path> loadAll() throws StorageException;

    Resource loadAsResource(String filename) throws FileNotFoundException;

    void deleteAll();

}