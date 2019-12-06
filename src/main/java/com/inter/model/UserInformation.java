package com.inter.model;

import java.util.List;

import lombok.Data;

@Data
public class UserInformation {
	
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String address;
	private String phoneNumber;
	private List<BankDetails> bankDetails;
	
	private ErrorModel errorModel;
}
