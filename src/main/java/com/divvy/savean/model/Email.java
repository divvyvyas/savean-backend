package com.divvy.savean.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

public @Data class Email
{
	@NotNull
	private String msgTo;
	@NotNull
	private String subject;
	@NotNull
	private String message;
}
