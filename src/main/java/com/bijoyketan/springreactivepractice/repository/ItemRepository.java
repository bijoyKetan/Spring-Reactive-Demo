package com.bijoyketan.springreactivepractice.repository;

import com.bijoyketan.springreactivepractice.domain.Item;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface ItemRepository extends ReactiveMongoRepository<Item, UUID> {
}
