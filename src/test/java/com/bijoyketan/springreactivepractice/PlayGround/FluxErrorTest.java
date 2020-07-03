package com.bijoyketan.springreactivepractice.PlayGround;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class FluxErrorTest {

    // Create flux with error
    //onErrorResume -> resumes with provided flux (new flux needs to be provided)
    //onErrorMap -> maps the encountered error to something else (e.g. another error)
    //onErrorReturn -> returns something when error is encountered


    @Test
    public void basicErrorTest (){
        var inputFlux = Flux.just("A", "B", "C", "D")
                .concatWith(Flux.error(new RuntimeException("Some exception occurred")))
                .concatWithValues("D")
                .onErrorResume(e -> {
                    return Flux.just("DefaultErrorValue");
                });

        StepVerifier.create(inputFlux)
                .expectSubscription()
                .expectNext("A", "B", "C", "D")
                .expectNext("DefaultErrorValue")
                .verifyComplete();
    }
}
