package com.inter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inter.Exception.InterException;
import com.inter.client.CrudClient;
import com.inter.model.BankDetails;
import com.inter.model.UserDetails;
import com.inter.model.UserInformation;

@Service
public class InterService {
	
	@Autowired
	CrudClient crudClient;

	public List<UserDetails> getAllUserBalance() throws InterException {
		List<UserDetails> userDetailList = null;
		List<UserInformation> userInformationList = crudClient.getAllUser();
		if(!StringUtils.isEmpty(userInformationList))
			userDetailList =  parseUserInformation(userInformationList);
		
		return userDetailList;
	}

	private List<UserDetails> parseUserInformation(List<UserInformation> userInformationList) {
		List<UserDetails> userDetailList = new ArrayList<>();
		for(UserInformation ui : userInformationList) {
			UserDetails userDetails = new UserDetails();
			userDetails.setFirstName(ui.getFirstName());
			userDetails.setLastName(ui.getLastName());
			userDetails.setEmail(ui.getEmail());
			userDetails.setPhoneNumber(ui.getPhoneNumber());
			userDetails.setUserId(ui.getUserId());
			userDetails.setAccountBalance(calculateTotalBalance(ui.getBankDetails()));
			userDetails.setEm(ui.getErrorModel());
			userDetailList.add(userDetails);
		}
		return userDetailList;
	}

	private double calculateTotalBalance(List<BankDetails> bankDetails) {
		return bankDetails.stream().mapToDouble(b -> b.getBalance()).sum();
	}

}
