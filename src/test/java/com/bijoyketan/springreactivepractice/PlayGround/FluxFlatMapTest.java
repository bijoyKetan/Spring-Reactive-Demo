package com.bijoyketan.springreactivepractice.PlayGround;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class FluxFlatMapTest {

    static int count = 0;

    @Test
    public void flatMapTest() {
        log.info(Stream.of("Amy", "Kara", "Ben", "Jerry", "Tom", "Robyn", "John", "Tamara", "Lida")
                .map(FluxFlatMapTest::getListFromString)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()).toString());
    }

    @Test
    public void testFlux_FlatMap() {
        var nameList = Arrays.asList("Amy", "Kara", "Ben", "Jerry", "Tom", "Robyn", "John");
        var testFlux = Flux.fromIterable(nameList)
                .concatWith(Flux.just("Tamara", "Lida"))
                //.map(this::getListFromString)
                //.flatMap(Flux::fromIterable);
                .flatMap(x -> Flux.fromIterable(getListFromString(x))); //this line same as the combined two lines above

        StepVerifier.create(testFlux.log())
                .expectSubscription()
                .expectNext("Amy", "Student", "Kara", "Engineer", "Ben", "Police", "Jerry", "Admin", "Tom",
                        "Trader", "Robyn", "Student", "John", "Engineer", "Tamara", "Police", "Lida", "Admin")
                .verifyComplete();
    }

    //Given a String, return a list of strings
    public static List<String> getListFromString(String inputString) {
        final var mockConcatStrList = Arrays.asList("Student", "Engineer", "Police", "Admin", "Trader");
        final var maxCount = mockConcatStrList.size();
        count = count >= maxCount ? 0 : count;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Arrays.asList(inputString, mockConcatStrList.get(count++));
    }

    public static void resetCounter() {
        count = 0;
    }
}
