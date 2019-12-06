package com.inter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class UserDetails {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phoneNumber;
	private double accountBalance;
	private ErrorModel em;
}
