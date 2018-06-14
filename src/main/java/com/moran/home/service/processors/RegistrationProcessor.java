/**
 * 
 */
package com.moran.home.service.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import com.moran.home.service.entity.user.User;
import com.moran.home.service.repository.AddressRepository;
import com.moran.home.service.repository.UserRepository;

/**
 * @author rahmohan
 *
 */
@Component
public class RegistrationProcessor implements Processor {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public void process(Exchange exchange) throws Exception {
		User user = (User) SerializationUtils.deserialize(exchange.getIn().getBody(byte[].class));
		addressRepository.save(user.getAddress());
		userRepository.save(user);
		exchange.getOut().setBody("User registered by Registration Processor");
	}

}
