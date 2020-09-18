package gurtovenko.DemoWebFlux.routers;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import gurtovenko.DemoWebFlux.handlers.GreetingHandler;

@Configuration
public class GreetingRouter {

	@Bean
	public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

		RequestPredicate route = RequestPredicates.GET("/hello").and(RequestPredicates.accept(MediaType.TEXT_PLAIN));
		
		return RouterFunctions.route(route, greetingHandler::hello)
				.andRoute(RequestPredicates.GET("/"), greetingHandler::index);
	}
}
