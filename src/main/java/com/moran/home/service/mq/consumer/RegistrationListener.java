package com.moran.home.service.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.moran.home.service.mq.utils.MqUtils;
import com.moran.home.service.soap.stubs.RegistrationRequest;

@Component
public class RegistrationListener {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationListener.class);

	@RabbitListener(queues = MqUtils.USER_REG_QUEUE )
	public void processMessage(String registrationRequestersEmail) {
		logger.info("Message received :--------------> " + registrationRequestersEmail);
	}

}
