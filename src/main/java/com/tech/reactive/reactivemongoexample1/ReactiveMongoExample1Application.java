package com.tech.reactive.reactivemongoexample1;

import com.tech.reactive.reactivemongoexample1.model.Destination;
import com.tech.reactive.reactivemongoexample1.repository.DestinationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveMongoExample1Application {

	@Bean
	CommandLineRunner destinations(DestinationRepository destinationRepository){
		return args -> {
			destinationRepository
					.deleteAll()
					.subscribe(null, null, () -> {
						Stream.of(new Destination(UUID.randomUUID().toString(),
								"Japan", "Tokyo"),
								new Destination(UUID.randomUUID().toString(),
										"Egypt", "Cairo"),
								new Destination(UUID.randomUUID().toString(),
										"France", "Paris"),
								new Destination(UUID.randomUUID().toString(),
										"USA", "DC")
								)
								.forEach(destination -> {
									destinationRepository
											.save(destination)
											.subscribe(System.out::println);
								});
					});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoExample1Application.class, args);
	}
}
