package com.inter.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inter.Exception.InterException;
import com.inter.model.UserDetails;
import com.inter.service.InterService;

@RunWith(MockitoJUnitRunner.class)
public class InterControllerTest {
		
		private MockMvc mockMvc;
		
		@Mock
		private InterService interService;
		
		@InjectMocks
		InterController interController;
		
		@Before
		public void init() {
			this.mockMvc = MockMvcBuilders.standaloneSetup(interController).build();
		}

		@Test
		public void AllProductShouldBeReturnedFromService() throws Exception {
			Mockito.when(interService.getAllUserBalance()).thenReturn(getListOfProduct());
			MvcResult result = this.mockMvc.perform(get("/getAllUserBalance").accept(MediaType.APPLICATION_JSON_VALUE))
															.andExpect(status().is(200))
															.andReturn();
			ObjectMapper mapper = new ObjectMapper();
			List<UserDetails> proList = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<UserDetails>>() {});
			assertNotNull(proList.get(0));
			assertEquals(getListOfProduct().get(0), proList.get(0));
		}
		
		@Test(expected=InterException.class)
		public void ErrorModelShouldBeReturnedFromService() throws Exception {
			Mockito.when(interService.getAllUserBalance()).thenReturn(null);
			interController.getAllUserBalance();
		}
		
		private List<UserDetails> getListOfProduct() {
			List<UserDetails> userDetailsList = new ArrayList<>();
			UserDetails userDetails = new UserDetails();
			userDetails.setAccountBalance(1200.00);
			userDetails.setEmail("abc@abc.com");
			userDetails.setFirstName("a");
			userDetails.setLastName("b");
			userDetails.setPhoneNumber("87543210");
			userDetails.setUserId(1);
			userDetailsList.add(userDetails);
			return userDetailsList;
		}
}
