package com.inter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.Exception.InterException;
import com.inter.model.ErrorModel;
import com.inter.model.UserDetails;
import com.inter.service.InterService;

@RestController
public class InterController {
	
	@Autowired
	InterService interService;

	@GetMapping("/getAllUserBalance")
	public ResponseEntity<List<UserDetails>> getAllUserBalance() throws InterException {
		List<UserDetails> userDetails = interService.getAllUserBalance();
		if(userDetails == null) {
			ErrorModel em = new ErrorModel(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server error");
			throw new InterException(em);
		}
			
		return new ResponseEntity<>(userDetails, HttpStatus.OK);
	}
}
