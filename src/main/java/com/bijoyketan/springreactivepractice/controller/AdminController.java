package com.bijoyketan.springreactivepractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AdminController {

    @GetMapping("api/version")
    public Mono<String> getVersion() {
        return Mono.just(ApiMetaData.API_VERSION);
    }
}
