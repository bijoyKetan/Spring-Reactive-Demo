package com.bijoyketan.springreactivepractice.initialize;

import com.bijoyketan.springreactivepractice.repository.ItemRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DataInitializerTest {

    private ItemRepository mongoRepository;

    @Test
    void getMultipleItems() {
        assertThat(new DataInitializer(mongoRepository).getMultipleItems(10))
                .hasSize(10);
        System.out.println(new DataInitializer(mongoRepository).getMultipleItems(10));
    }
}