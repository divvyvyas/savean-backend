package com.divvy.savean.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.divvy.savean.dao.FilesDao;
import com.divvy.savean.exception.FileStorageException;
import com.divvy.savean.exception.MyFileNotFoundException;
import com.divvy.savean.model.Files;

@Service
@Transactional
public class FileStorageServiceImpl implements FileStorageService
{
	@Autowired
    private FilesDao filesDao;

	@Override
    public Files storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Files dbFile = new Files(fileName, file.getContentType(), file.getBytes());

            return filesDao.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

	@Override
    public Files getFile(String fileId) {
        return filesDao.findById(Long.parseLong(fileId))
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
