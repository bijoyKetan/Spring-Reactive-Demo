package com.bijoyketan.springreactivepractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController(value = "/")
public class ItemController {

    @GetMapping("/test")
    public Mono<String> testApi() {
        return Mono.just("version 1");
    }
}
