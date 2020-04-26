package com.divvy.savean.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divvy.savean.model.Report;

@Repository
public interface ReportDao extends JpaRepository<Report, Long> {

}