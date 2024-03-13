package com.grp3.bid.entities;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @NotNull
    private Integer id;
    @NotNull
    private Float value;
    @NotNull
    private LocalDateTime offerDateTime;
    @NotNull
    private User user;
    @NotNull
    private Product product;
    @Nullable
    private Address address_delivery;

    public Offer(Float value, LocalDateTime offerDateTime, User user, Product product, Address address_delivery) {
        this.value = value;
        this.offerDateTime = offerDateTime;
        this.user = user;
        this.product = product;
        this.address_delivery = address_delivery;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Offer{");
        sb.append("id=").append(id);
        sb.append(", value=").append(value);
        sb.append(", offerDateTime=").append(offerDateTime);
        sb.append(", user=").append(user);
        sb.append(", product=").append(product);
        sb.append(", address_delivery=").append(address_delivery);
        sb.append('}');
        return sb.toString();
    }
}
