package com.tech.reactive.reactivemongoexample1.resource;

import com.tech.reactive.reactivemongoexample1.model.Destination;
import com.tech.reactive.reactivemongoexample1.model.DestinationEvent;
import com.tech.reactive.reactivemongoexample1.repository.DestinationRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.awt.*;
import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("/rest/destination")
public class DestinationResource {

    private DestinationRepository destinationRepository;

    public DestinationResource(DestinationRepository destinationRepository){
        this.destinationRepository = destinationRepository;
    }

    @GetMapping("/all")
    public Flux<Destination> getAll(){
        return destinationRepository
                .findAll();
    }

    @GetMapping("/{id}")
    public Mono<Destination> getId(@PathVariable final String destId) {
        return destinationRepository.findById(destId);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<DestinationEvent> getEvents(@PathVariable("id") final String destId){
        return destinationRepository.findById(destId)
            .flatMapMany(destination -> {
                Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));

                Flux<DestinationEvent> destinationEventFlux =
                        Flux.fromStream(
                                Stream.generate(() -> new DestinationEvent(destination,
                                        new Date()))
                        );

                return Flux.zip(interval, destinationEventFlux)
                        .map(Tuple2::getT2);
        });
    }

    @CrossOrigin(origins = {"http://localhost:3000"})
    @PostMapping("/submit-destination")
    public Mono<Destination> submitLocation(@RequestBody String location) {
        return destinationRepository.insert(new Destination(UUID.randomUUID().toString(),
                location, null));
    }

}
