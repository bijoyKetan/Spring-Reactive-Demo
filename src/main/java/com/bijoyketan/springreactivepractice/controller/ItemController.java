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
    //Test code push for pushing image to DockerHub
    @GetMapping("/items")
    public Flux<Item> getAllItems() {
        return itemRepository.findAll();
    }

    //Get one item given ID
    @GetMapping("/items/item")
    public Mono<ResponseEntity<Item>> getOneItem(@RequestParam String id) {
        return itemRepository.findById(UUID.fromString(id))
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create an item
    @PostMapping("/items")
    public Mono<ResponseEntity<Item>> createItem(@RequestBody Item item) {
        return itemRepository.save(item)
                .map(createdItem -> new ResponseEntity<>(createdItem, HttpStatus.CREATED));
    }

    // Put mapping - modify and existing item otherwise create it
    @PutMapping("/items/item")
    public Mono<ResponseEntity<Item>> updateItem(@RequestBody Item item) {
        return itemRepository.findById(item.getProductID())
                .flatMap(existingItem -> {
                    existingItem.setColor(item.getColor());
                    existingItem.setName(item.getName());
                    existingItem.setPrice(item.getPrice());
                    existingItem.setNearbyStores(item.getNearbyStores());
                    existingItem.setYearFirstAvailable(item.getYearFirstAvailable());
                    return itemRepository.save(existingItem);
                })
                .map(updatedItem -> new ResponseEntity<>(updatedItem, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete mapping - delete the item if that exists
    @DeleteMapping("/items/item")
    public Mono<ResponseEntity<Void>> deleteItem(@RequestParam String id) {
        return itemRepository.findById(UUID.fromString(id))
                .flatMap(existingItem -> itemRepository.delete(existingItem)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
