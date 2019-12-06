package com.inter.model;

import lombok.Data;

@Data
public class BankDetails {

	private int id;
	private String bankName;
	private String accountNumber;
	private double balance;
}
