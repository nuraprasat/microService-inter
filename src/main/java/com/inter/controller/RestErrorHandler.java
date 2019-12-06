package com.inter.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.inter.Exception.InterException;
import com.inter.model.ErrorModel;
import com.inter.model.UserDetails;

@ControllerAdvice
@RequestMapping(produces = "application/json")
public class RestErrorHandler {
	
	private static final String INTERNAL_SERVER_ERROR_LOG = "Internal Server Error - ";
	private static final Logger LOGGER = Logger.getLogger(RestErrorHandler.class.getName());
	
	@ExceptionHandler(InterException.class)
	public ResponseEntity<UserDetails> hadleInternalError(final InterException e) {
		LOGGER.log(Level.WARNING,INTERNAL_SERVER_ERROR_LOG+e.getErrorModel());
		return error(e.getErrorModel());
	}
	
	private ResponseEntity<UserDetails> error(final ErrorModel em) {
		UserDetails p = getProduct(em);
		return new ResponseEntity<>(p, HttpStatus.BAD_REQUEST);
	}
	
	private UserDetails getProduct(ErrorModel em) {
		UserDetails ud = new UserDetails();
		ud.setEm(em);
		return ud;
	}

	
}
