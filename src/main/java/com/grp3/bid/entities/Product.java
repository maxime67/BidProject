package com.grp3.bid.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Le nom est obligatoire")
    private String name;
    @NotBlank(message = "La description est obligatoire")
    private String description;
    @NotNull(message = "Le prix de depart est obligatoire")
    private Long startingValue;
    @NotBlank
    private String pathToImg;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private User seller;
    private Category category;

    public Product(String name, String description, Long startingValue, String pathToImg, LocalDateTime startDate, LocalDateTime endDate, User seller, Category category) {
        this.name = name;
        this.description = description;
        this.startingValue = startingValue;
        this.pathToImg = pathToImg;
        this.startDate = startDate;
        this.endDate = endDate;
        this.seller = seller;
        this.category = category;
    }
}
