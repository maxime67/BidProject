package com.grp3.bid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String description;
    private Long startingValue;
    private String pathToImg;
    private LocalDate startDate;
    private LocalDate endDate;
    private User vendor_user;

    public Product(String name, String description, Long startingValue, String pathToImg, LocalDate startDate, LocalDate endDate, User vendor_user) {
        this.name = name;
        this.description = description;
        this.startingValue = startingValue;
        this.pathToImg = pathToImg;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vendor_user = vendor_user;
    }
}
