package com.moran.home.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

	private static final Logger logger = LoggerFactory.getLogger(UserRouter.class);

	@Bean(name = "hellomoto")
	public RouterFunction<ServerResponse> route(UserHandler userHandler) {
		logger.info("Entering the ROUTER........................................");
		return RouterFunctions.route(RequestPredicates.GET("/user").and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)), userHandler::user);
	}
}
