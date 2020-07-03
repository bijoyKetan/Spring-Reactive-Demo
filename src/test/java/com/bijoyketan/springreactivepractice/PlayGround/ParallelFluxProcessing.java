package com.bijoyketan.springreactivepractice.PlayGround;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static com.bijoyketan.springreactivepractice.PlayGround.FluxFlatMapTest.resetCounter;
import static reactor.core.scheduler.Schedulers.parallel;

public class ParallelFluxProcessing {

    @Test
    public void testMergedFlux() {
        var fluxA = Flux.just("A", "B", "C");
        var fluxB = Flux.just("E", "F", "G");

        var mergedFlux = fluxA.mergeWith(fluxB);
        StepVerifier.create(mergedFlux.log())
                .expectSubscription()
                .expectNext("A", "B", "C", "E", "F", "G")
                .verifyComplete();
    }

    @Test
    public void testMergedFlux_Parallel() {
        resetCounter();
        var flux = Flux.fromIterable(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"))
                .window(2)
                .flatMapSequential(s -> s.map(FluxFlatMapTest::getListFromString).subscribeOn(parallel()))
                .flatMap(Flux::fromIterable)
                .log();

        StepVerifier.create(flux)
                .expectNextCount(16)
                .verifyComplete();
    }
}
