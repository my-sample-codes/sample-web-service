package com.moran.home.service.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.moran.home.service.mq.utils.MqUtils;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class RegistrationRoute extends RouteBuilder {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationRoute.class);

	@Value("${spring.rabbitmq.virtualHost}")
	private String RABBIT_VHOST;

	@Value("${spring.rabbitmq.username}")
	private String RABBIT_USER;

	@Value("${spring.rabbitmq.password}")
	private String RABBIT_PASS;

	@Bean(name = "camelRabbitConnectionFactory")
	public ConnectionFactory getCamelRabbitConnectionFactory() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setVirtualHost(RABBIT_VHOST);
		connectionFactory.setUsername(RABBIT_USER);
		connectionFactory.setPassword(RABBIT_PASS);
		return connectionFactory;
	}

	@Override
	public void configure() throws Exception {
		try {
			from("rabbitmq://localhost:5672/" + MqUtils.USER_EXCHANGE + "?queue=" + MqUtils.USER_REG_QUEUE
					+ "&autoAck=true&autoDelete=false&durable=true&exchangeType=direct&connectionFactory=#camelRabbitConnectionFactory")
							.to("registrationProcessor").to("log:foo");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
