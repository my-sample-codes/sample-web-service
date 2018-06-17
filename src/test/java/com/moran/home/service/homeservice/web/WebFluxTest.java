package com.moran.home.service.homeservice.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.moran.home.service.entity.user.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebFluxTest {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void testHello() {
		webTestClient.get().uri("/user?userName=madhurima").accept(MediaType.APPLICATION_JSON).exchange()
				.expectBody(User.class);
		
		FluxExchangeResult<User> user = webTestClient.get().uri("/user?userName=malovika").accept(MediaType.APPLICATION_JSON).exchange().returnResult(User.class);
		
		System.out.println("heeeeeeeeeeeeee---->>>>>>>>>>>>>"+user.getResponseBody().blockFirst());
		/*
		 * @Test public void testEcho() {
		 * this.webClient.post().uri("/echo").contentType(MediaType.TEXT_PLAIN)
		 * .accept(MediaType.TEXT_PLAIN) .body(Mono.just("Hello WebFlux!"),
		 * String.class).exchange()
		 * .expectBody(String.class).isEqualTo("Hello WebFlux!"); }
		 */
	}
}
