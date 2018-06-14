package com.moran.home.service.soap.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moran.home.service.entity.address.Address;
import com.moran.home.service.entity.user.User;
import com.moran.home.service.mq.utils.MqConstants;
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
		amqpAdmin.declareExchange(ExchangeBuilder.topicExchange(MqConstants.USER_EXCHANGE).build());
		String queue = amqpAdmin.declareQueue(new Queue(MqConstants.USER_REG_QUEUE));
		amqpAdmin.declareBinding(new Binding(queue, Binding.DestinationType.QUEUE,
				MqConstants.USER_EXCHANGE, MqConstants.USER_REG_ROUTING_KEY, null));
	}

	public boolean register(RegistrationRequest request) {
		try {
			User user = new User();
			user.setUserName(request.getUserName());
			user.setFirstName(request.getFirstName());
			user.setMiddleName(request.getMiddleName());
			user.setLastName(request.getLastName());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword().toCharArray());

			Address address = new Address();
			com.moran.home.service.soap.stubs.Address reqAdress = request.getAddress();
			address.setAddressLine(reqAdress.getAddressLine());
			address.setCity(reqAdress.getCity());
			address.setDistrict(reqAdress.getDistrict());
			address.setState(reqAdress.getState());
			address.setCountry(reqAdress.getCountry());
			address.setPin(reqAdress.getPin());

			user.setAddress(address);

			logger.info("Sending message to Queue..............");
			amqpTemplate.convertAndSend(MqConstants.USER_EXCHANGE, MqConstants.USER_REG_ROUTING_KEY, user);
			return true;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
