package gurtovenko.DemoWebFlux.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gurtovenko.DemoWebFlux.domain.Message;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/controller")
public class MainController {
	
	@GetMapping
	public Flux<Message> list(
								@RequestParam(defaultValue = "0") Long start,
								@RequestParam(defaultValue = "3") Long count){
		return Flux
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
	}
}
