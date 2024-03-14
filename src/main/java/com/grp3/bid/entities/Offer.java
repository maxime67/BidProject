package com.grp3.bid.entities;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer implements Serializable {
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
    private Address deliveryAddress;

    public Offer(Float value, LocalDateTime offerDateTime, User user, Product product, Address deliveryAddress) {
        this.value = value;
        this.offerDateTime = offerDateTime;
        this.user = user;
        this.product = product;
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Offer{");
        sb.append("id=").append(id);
        sb.append(", value=").append(value);
        sb.append(", offerDateTime=").append(offerDateTime);
        sb.append(", user=").append(user);
        sb.append(", product=").append(product);
        sb.append(", address_delivery=").append(deliveryAddress);
        sb.append('}');
        return sb.toString();
    }
}
