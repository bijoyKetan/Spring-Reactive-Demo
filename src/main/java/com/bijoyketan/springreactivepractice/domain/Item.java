package com.bijoyketan.springreactivepractice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    private String name;
    private double weightInKg;
    private int yearFirstAvailable;
    private List<String> nearbyStores;
    private String color;
    private double price;
}
