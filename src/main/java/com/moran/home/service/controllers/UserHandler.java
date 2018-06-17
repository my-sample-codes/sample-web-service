package com.moran.home.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.moran.home.service.repository.UserRepository;

import reactor.core.publisher.Mono;

@Component(value="homeServiceUserHandler")
public class UserHandler {
	private static final Logger logger = LoggerFactory.getLogger(UserHandler.class);

	@Autowired
	private UserRepository userRepository;

	public Mono<ServerResponse> user(ServerRequest serverRequest) {
		logger.info("Entering the User Handler.................................");
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(
				BodyInserters.fromObject(userRepository.findById(serverRequest.queryParam("userName").get()).get()));
	}
}
