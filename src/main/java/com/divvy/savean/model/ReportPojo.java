package com.divvy.savean.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

public @Data class ReportPojo
{
	private long id;
	private String email;
	private String name;
	private MultipartFile file;
	private String message;
	private String latitude;
	private String longitude;
}
