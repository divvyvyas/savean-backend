package com.divvy.savean.model;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
public @Data class Report
{
	@Transient
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	Random r = new Random();
	@Id
	private long id = r.nextInt(899999)+100000;
	private String email;
	private String name;
	private String filePath;
	@Column(length = 10000)
	private String message;
	private String latitude;
	private String longitude;
}
