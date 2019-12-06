package com.inter.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.inter.Exception.InterException;
import com.inter.model.ErrorModel;
import com.inter.model.UserInformation;

@Component
public class CrudClient {
	private static final Logger LOGGER = Logger.getLogger(CrudClient.class.getName());
	
	@Value("${crud.URL}")
	private String crudUrl;
	@Autowired
	private RestTemplate restTemplate;

	public List<UserInformation> getAllUser() throws InterException {
		List<UserInformation> userInformationList = new ArrayList<>();
		try {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			ResponseEntity<List<UserInformation>> response = restTemplate.exchange(crudUrl,HttpMethod.GET, null, new ParameterizedTypeReference<List<UserInformation>>() {});
			stopWatch.stop();
			LOGGER.info("Total elapsed http request/response time for source system: PAM_AC in milliseconds: {"+stopWatch.getTotalTimeMillis()+"}");
			if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
				userInformationList = response.getBody();
			}
		} catch (RestClientResponseException e) {
			LOGGER.log(Level.WARNING,"Unable to retrieve result");
			throw new InterException(new ErrorModel(422, "Unable to connect to CRUD"));
		}
		return userInformationList;
	}

}
