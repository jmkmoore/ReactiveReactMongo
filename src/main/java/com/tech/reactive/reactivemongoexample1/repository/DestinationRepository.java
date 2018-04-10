package com.tech.reactive.reactivemongoexample1.repository;

import com.tech.reactive.reactivemongoexample1.model.Destination;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface DestinationRepository extends ReactiveMongoRepository<Destination, String> {
}
