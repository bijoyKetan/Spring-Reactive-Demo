package com.bijoyketan.springreactivepractice.PlayGround;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.stream.Stream;

@Slf4j
public class FluxAndMonoTest {

    @Test
    public void monoTest() {
        Mono<String> mono = Mono.just("Amy")
                .log();

        StepVerifier.create(mono)
                .expectNext("Amy")
                .expectComplete()
                .verify();
    }

    @Test
    public void fluxTest() {
        Flux<String> flux = Flux.just("Canada", "USA", "China", "Mexico")
                .log();

        StepVerifier.create(flux)
                .expectNext("Canada")
                .expectNext("USA")
                .expectNext("China")
                .expectNext("Mexico")
                .expectComplete()
                .verify();
    }

    @Test
    public void fluxTestWithIterable() {
        Flux<String> fluxIterable = Flux.fromIterable(Arrays.asList("Apple", "Sony", "Bose", "Caterpillar", "Dodge"))
                .concatWithValues("EAGames", "FA");

        StepVerifier.create(fluxIterable)
                .expectNext("Apple", "Sony", "Bose", "Caterpillar", "Dodge", "EAGames", "FA")
                .verifyComplete();
    }

    @Test
    public void fluxStreams() {
        Stream<String> s = Stream.of("Dhaka", "Sylhet", "Rajshahi", "Dinajpur");
        Flux<String> streamFlux = Flux.fromStream(s);

        StepVerifier.create(streamFlux.log())
                .expectNext("Dhaka", "Sylhet", "Rajshahi", "Dinajpur")
                .verifyComplete();
    }

    @Test
    public void fluxFilterMap() {
        val nameList = Arrays.asList("Apple", "Sony", "Bose", "Caterpillar", "Dodge");
        Flux<String> nameFlux = Flux.fromIterable(nameList)
                .filter(name -> name.toUpperCase().contains("S"))
                .map(String::toLowerCase);

        StepVerifier.create(nameFlux)
                .expectNext("sony", "bose")
                .verifyComplete();
    }
}
