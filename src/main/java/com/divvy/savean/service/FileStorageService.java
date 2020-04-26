package com.divvy.savean.service;

import org.springframework.web.multipart.MultipartFile;

import com.divvy.savean.model.Files;

public interface FileStorageService {

	Files storeFile(MultipartFile file);

	Files getFile(String fileId);

}
