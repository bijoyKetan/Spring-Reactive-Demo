package com.bijoyketan.springreactivepractice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

@Slf4j
public class PlayGround {

    @Test
    public void testWithMonoFlux() {
        Flux.just("Amy", "Sam", "Nancy")
                .subscribe();
    }
}
