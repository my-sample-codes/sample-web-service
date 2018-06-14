/**
 * 
 */
package com.moran.home.service.routes;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author rahmohan
 *
 */
@Component
public class FileMoverRoute extends RouteBuilder {
	private static Logger logger = LoggerFactory.getLogger(FileMoverRoute.class);

	@Override
	public void configure() throws Exception {
		logger.info("Starting camel routes..................................");
		from("file:/tmp/camel-test/source/").to("file:/tmp/camel-test/target/");
	}

}
