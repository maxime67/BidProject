package com.grp3.bid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime dateFinal;
    private User vendor_user;

    public Product(String name, String description, Long startingValue, String pathToImg, LocalDateTime dateFinal, User vendor_user) {
        this.name = name;
        this.description = description;
        this.startingValue = startingValue;
        this.pathToImg = pathToImg;
        this.dateFinal = dateFinal;
        this.vendor_user = vendor_user;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", startingvalue=").append(startingValue);
        sb.append(", pathToImg='").append(pathToImg).append('\'');
        sb.append(", dateFinal=").append(dateFinal);
        sb.append(", vendor_user=").append(vendor_user);
        sb.append('}');
        return sb.toString();
    }
}
