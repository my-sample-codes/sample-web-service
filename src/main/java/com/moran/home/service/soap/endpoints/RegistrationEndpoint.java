package com.moran.home.service.soap.endpoints;

import org.apache.tomcat.util.security.MD5Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.moran.home.service.soap.services.RegistrationService;
import com.moran.home.service.soap.stubs.RegistrationRequest;
import com.moran.home.service.soap.stubs.RegistrationResponse;

@Endpoint
public class RegistrationEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationEndpoint.class);
	private static final String NAMESPACE_URI = "http://www.moran.com/home/service/soap/stubs";

	@Autowired
	private RegistrationService registrationService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "registrationRequest")
	@ResponsePayload
	public RegistrationResponse register(@RequestPayload RegistrationRequest request) {
		RegistrationResponse response = new RegistrationResponse();
		boolean isRegistered = registrationService.register(request);
		if (isRegistered) {
			logger.info("User Registered");
			response.setRegistrationSequence(MD5Encoder.encode(request.getUserName().getBytes()));
			response.setSuccess(isRegistered);
		} else {
			logger.error("User unable to register");
			response.setFullName("User unable to register");
			response.setSuccess(isRegistered);
		}
		return response;
	}

}
