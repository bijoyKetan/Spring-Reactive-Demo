package com.bijoyketan.springreactivepractice.PlayGround;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FluxFlatMapTest {

    int count = 0;

    //Given a String, return a list of strings
    public List<String> getListFromString(String inputString) {
        final var mockConcatStrList = Arrays.asList("Student", "Engineer", "Police", "Admin", "Trader");
        final var maxCount = mockConcatStrList.size();
        count = count >= maxCount ? 0 : count;
        return Arrays.asList(inputString, mockConcatStrList.get(count++));
    }

    @Test
    public void flatMapTest() {
        System.out.println(Stream.of("Amy", "Kara", "Ben", "Jerry", "Tom", "Robyn", "John")
                .map(this::getListFromString)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }
}
