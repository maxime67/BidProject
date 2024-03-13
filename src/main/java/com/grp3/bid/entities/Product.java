package com.grp3.bid.entities;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Integer id;
    @NotNull
    private String name;
    @Nullable
    private String description;
    @NotNull
    private Long startingValue;
    @Nullable
    private String pathToImg;
    @NotNull
    private LocalDateTime dateFinal;
    @NotNull
    private User vendorUser;
    @NotNull
    private Category category;
    @Nullable
    private User buyerUser;
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
