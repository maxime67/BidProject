package com.grp3.bid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Long startingValue;
    private String pathToImg;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String category;
    private User seller;

    public Product(String name, String description, Long startingValue, String pathToImg, LocalDateTime startDate, LocalDateTime endDate, String category, User seller) {
        this.name = name;
        this.description = description;
        this.startingValue = startingValue;
        this.pathToImg = pathToImg;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
        this.seller = seller;
    }
}
