package com.bijoyketan.springreactivepractice.controller;

import com.bijoyketan.springreactivepractice.domain.Item;
import com.bijoyketan.springreactivepractice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(ApiMetaData.API_ROOT)
@Slf4j
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //Find all items asynchronously
    @GetMapping("/items")
    public Flux<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //Get one item given ID
    @GetMapping
    public Mono<ResponseEntity<Item>> getOneItem(String id) {
        return itemRepository.findById(UUID.fromString(id))
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Post mapping - create an item
    @PostMapping("/items")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Item>> createItem(@RequestBody Item item) {
        if (!itemRepository.findById(item.getProductID()).equals(Mono.empty())) {
            return null;
        } else {

            return itemRepository.save(item)
                    .map(createdItem -> new ResponseEntity<>(createdItem, HttpStatus.CREATED));
        }
    }

    // putmapping - modify and existing item otherwise create it
    // deletemapping - delete the item if that exists
}
