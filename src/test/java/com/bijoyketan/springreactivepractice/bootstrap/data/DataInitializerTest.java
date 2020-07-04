package com.bijoyketan.springreactivepractice.bootstrap.data;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DataInitializerTest {

    @Test
    void getMultipleItems() {
        assertThat(new DataInitializer().getMultipleItems(10))
                .hasSize(10);
        System.out.println(new DataInitializer().getMultipleItems(10));
    }
}