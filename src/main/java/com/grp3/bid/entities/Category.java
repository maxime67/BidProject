package com.grp3.bid.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @NotNull
    private Long idCategory;
    @NotNull
    private String nameCategory;
}
