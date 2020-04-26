package com.divvy.savean.model;

import lombok.Data;

public @Data class PasswordPojo
{
	private String username;
	private String currentPassword;
	private String newPassword;
}
