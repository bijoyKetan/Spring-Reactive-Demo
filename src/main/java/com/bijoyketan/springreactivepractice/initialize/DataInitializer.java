package com.bijoyketan.springreactivepractice.initialize;

import com.bijoyketan.springreactivepractice.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final ReactiveCrudRepository<Item, UUID> repository;
    private final List<String> names = Arrays.asList("Heinz", "Bose", "SanDisk", "Apple", "SamSung");
    private final List<String> colors = Arrays.asList("Red", "Blue", "Green", "Yellow", "Orange");
    private final List<List<String>> nearbyStores = Arrays.asList(
            Arrays.asList("Kearny", "Harrison", "WestOrange"),
            Arrays.asList("Paterson", "Trenton"),
            Arrays.asList("Chester", "Mansfield", "Haledon"),
            Arrays.asList("JerseyCity", "Hoboken", "Newport"),
            Arrays.asList("ExchangePl", "GroveSt", "Newark")
    );
    private int count = 0;
    private static final int NUMBER_ITEMS = 10;

    @Autowired
    public DataInitializer(ReactiveCrudRepository<Item, UUID> repository) {
        this.repository = repository;
    }

    public List<Item> getMultipleItems(int numberOfItems) {
        Random r = new Random();
        return IntStream
                .range(0, numberOfItems)
                .mapToObj(i ->
                        Item.builder()
                                .productID(UUID.randomUUID())
                                .name(names.get(pickIndex()))
                                .color(colors.get(pickIndex()))
                                .price(r.nextInt(100) * r.nextDouble())
                                .yearFirstAvailable(r.nextInt(101) + 1900)
                                .nearbyStores(nearbyStores.get(pickIndex()))
                                .build()
                )
                .collect(Collectors.toList());
    }

    private int pickIndex() {
        final var inputListSize = names.size();
        count = count >= inputListSize ? 0 : count++;
        return count++;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll()
                .thenMany(Flux.fromIterable(getMultipleItems(10)))
                .flatMap(repository::save)
                .thenMany(repository.findAll())
                .subscribe((item -> {
                    log.info("Item inserted from CommandLineRunner : " + item);
                }));
    }
}
