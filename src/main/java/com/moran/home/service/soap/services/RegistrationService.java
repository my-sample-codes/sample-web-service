package com.moran.home.service.soap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.moran.home.service.soap.stubs.RegistrationRequest;

@Service
public class RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

	public boolean register(RegistrationRequest request) {
		logger.info(request.getEmail());
		logger.info(request.getFullName());
		logger.info(request.getUserName());
		logger.info(request.getAddress().getAddressLine());
		logger.info(request.getAddress().getDistrict());
		return true;
	}

}
