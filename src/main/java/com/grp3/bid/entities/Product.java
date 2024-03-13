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
    private LocalDateTime dateFinal;
    private User vendorUser;
    private Category category;

    public Product(String name, String description, Long startingValue, String pathToImg, LocalDateTime dateFinal, User vendor_user, Category category) {
        this.name = name;
        this.description = description;
        this.startingValue = startingValue;
        this.pathToImg = pathToImg;
        this.dateFinal = dateFinal;
        this.vendorUser = vendor_user;
        this.category = category;
    }
}
