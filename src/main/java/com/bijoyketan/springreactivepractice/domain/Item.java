package com.bijoyketan.springreactivepractice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    private UUID productID;
    private String name;
    private String color;
    private double price;
    private int yearFirstAvailable;
    private List<String> nearbyStores;
}
