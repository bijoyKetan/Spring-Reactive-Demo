package com.bijoyketan.springreactivepractice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

@Slf4j
public class PlayGround {

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
                .expectNext("Amy")
                .expectNext("Sam")
                .expectNext("nancy")
                .expectNext("Lida")
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
}
