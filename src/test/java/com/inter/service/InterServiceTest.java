package com.inter.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.inter.client.CrudClient;
import com.inter.model.BankDetails;
import com.inter.model.UserDetails;
import com.inter.model.UserInformation;

@RunWith(MockitoJUnitRunner.class)
public class InterServiceTest {
	@InjectMocks
	InterService interService;
	
	@Mock
	CrudClient crudClient;
	
	@Test
	public void checkRetrieveValidOutput() throws Exception {
		Mockito.when(crudClient.getAllUser()).thenReturn(getListOfProduct());
		List<UserDetails> userDetailList = interService.getAllUserBalance();
		assertNotNull(userDetailList);
		assertEquals(userDetailList.get(0).getEmail(), getListOfProduct().get(0).getEmail());
	}
	
	@Test
	public void checkemptyList() throws Exception {
		Mockito.when(crudClient.getAllUser()).thenReturn(null);
		List<UserDetails> userDetailList = interService.getAllUserBalance();
		Mockito.verify(crudClient, Mockito.times(1)).getAllUser();
		assertNull(userDetailList);
	}
	
	private List<UserInformation> getListOfProduct() {
		List<UserInformation> userDetailsList = new ArrayList<>();
		UserInformation userInformation = new UserInformation();
		userInformation.setBankDetails(getBankDetails());
		userInformation.setEmail("abc@abc.com");
		userInformation.setFirstName("a");
		userInformation.setLastName("b");
		userInformation.setPhoneNumber("87543210");
		userInformation.setUserId(1);
		userDetailsList.add(userInformation);
		return userDetailsList;
	}

	private List<BankDetails> getBankDetails() {
		List<BankDetails> list = new ArrayList<>();
		BankDetails bankDetails = new BankDetails();
		bankDetails.setAccountNumber("123abc");
		bankDetails.setBalance(120.0);
		bankDetails.setBankName("icici");
		bankDetails.setId(1);
		list.add(bankDetails);
		list.add(bankDetails);
		return list;
	}
}
