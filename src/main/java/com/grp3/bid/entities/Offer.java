package com.grp3.bid.entities;

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
    private Integer id;
    private Long value;
    private LocalDateTime offerDateTime;
    private User user;
    private Product product;
    private Address deliveryAddress;

    public Offer(Long value, LocalDateTime offerDateTime, User user, Product product, Address deliveryAddress) {
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
