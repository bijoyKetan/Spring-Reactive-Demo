package com.bijoyketan.springreactivepractice.controller;

import com.bijoyketan.springreactivepractice.domain.Item;
import com.bijoyketan.springreactivepractice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(ApiMetaData.API_ROOT)
@Slf4j
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/monotest")
    public Mono<String> testMono() {
        return Mono.just("version 1");
    }

    @GetMapping("/fluxtest")
    public Flux<String> testFlux() {
        return Flux.just("item 1", "item 2");
    }

    @GetMapping("/items")
    public Flux<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
