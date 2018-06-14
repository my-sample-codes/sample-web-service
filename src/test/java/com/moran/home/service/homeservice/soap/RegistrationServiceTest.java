package com.moran.home.service.homeservice.soap;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.moran.home.service.soap.stubs.Address;
import com.moran.home.service.soap.stubs.RegistrationRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RegistrationServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(RegistrationServiceTest.class);
	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

	@LocalServerPort
	private int port = 0;

	@Before
	public void init() throws Exception {
		logger.info("Running Before test");
		marshaller.setPackagesToScan(ClassUtils.getPackageName(RegistrationRequest.class));
		marshaller.afterPropertiesSet();
	}

	@Test
	public void testSendAndReceive() {
		logger.info("Testing started......");
		WebServiceTemplate ws = new WebServiceTemplate(marshaller);
		RegistrationRequest request = new RegistrationRequest();
		request.setUserName("rahul");
		request.setEmail("rahul@test.com");
		request.setPassword("rahul");
		request.setFirstName("RAHUL");
		request.setLastName("MOHAN");

		Address address = new Address();
		address.setAddressLine("Ushapur");
		address.setCity("Moranhat");
		address.setDistrict("Sibsagar");
		address.setState("Assam");
		address.setCountry("india");
		address.setPin(785670);

		request.setAddress(address);

		assertNotNull(ws.marshalSendAndReceive("http://localhost:" + port + "/ws", request));
	}

}
