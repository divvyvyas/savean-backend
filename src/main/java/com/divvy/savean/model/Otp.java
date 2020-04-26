package com.divvy.savean.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
public @Data class Otp {
	private long otp;
	private String email;
}
