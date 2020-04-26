package com.divvy.savean.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divvy.savean.model.Files;

@Repository
public interface FilesDao extends JpaRepository<Files, Long> {

}
