package gurtovenko.DemoWebFlux.handlers;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import gurtovenko.DemoWebFlux.domain.Message;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

//	public Mono<ServerResponse> hello(ServerRequest request) {
//		BodyInserter<String, ReactiveHttpOutputMessage> body = BodyInserters.fromValue("Hello, Spring!");
//
//		return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(body);
//	}
	public Mono<ServerResponse> hello(ServerRequest serverRequest) {
		Long start = serverRequest.queryParam("start").map(Long::valueOf).orElse(0l);
		
		Long count = serverRequest.queryParam("count").map(Long::valueOf).orElse(3l);
		
		Flux<Message> data = Flux
				.just(
						"Hello, Reactive",
						"More then one",
						"Third post",
						"fourth post",
						"five post"
						)
						.skip(start)
						.take(count)
						.map(Message::new);

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(data, Message.class);
	}
	
	public Mono<ServerResponse> index(ServerRequest serverRequest) {
		String user = serverRequest.queryParam("user").orElse("Nobody");

		return ServerResponse.ok().render("index", Map.of("user", user));
	  }

}
