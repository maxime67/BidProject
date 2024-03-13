package com.grp3.bid.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    private Double startingValue;
    @NotBlank
    private String pathToImg;
    @NotNull(message = "La date est obligatoire")
    private LocalDateTime startDate;
    @NotNull
    private LocalDateTime endDate;
    @NotBlank
    private String category;
    private User seller;

    public Product(String name, String description, Double startingValue, String pathToImg, LocalDateTime startDate, LocalDateTime endDate, String category, User seller) {
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
