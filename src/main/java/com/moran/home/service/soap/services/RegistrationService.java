package com.moran.home.service.soap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moran.home.service.mq.utils.MqUtils;
import com.moran.home.service.soap.stubs.RegistrationRequest;

@Service
public class RegistrationService {

	private static final Logger logger = LoggerFactory.getLogger(RegistrationService.class);

	private final AmqpAdmin amqpAdmin;
	private final AmqpTemplate amqpTemplate;

	@Autowired
	public RegistrationService(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
		this.amqpAdmin = amqpAdmin;
		this.amqpTemplate = amqpTemplate;

		amqpAdmin.declareExchange(ExchangeBuilder.directExchange(MqUtils.USER_EXCHANGE).build());
		amqpAdmin.declareQueue(new Queue(MqUtils.USER_REG_QUEUE));
	}

	public boolean register(RegistrationRequest request) {
		logger.info(request.getEmail());
		logger.info(request.getUserName());
		logger.info(request.getAddress().getAddressLine());
		logger.info(request.getAddress().getDistrict());

		logger.info("Sending message to Queue..............");
		amqpTemplate.convertAndSend(MqUtils.USER_EXCHANGE, MqUtils.USER_REG_ROUTING_KEY, request.getEmail());
		return true;
	}

}
