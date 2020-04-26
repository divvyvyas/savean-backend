package com.divvy.savean.email;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig
{
	@NotNull
	@Value("${spring.mail.host}")
	private String host;
	
	@NotNull
	@Value("${spring.mail.port}")
	private int port;
	
	@NotNull
	@Value("${spring.mail.username}")
	private String username;
	
	@NotNull
	@Value("${spring.mail.password}")
	private String password;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}