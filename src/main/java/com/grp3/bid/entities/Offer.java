package com.grp3.bid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    private Integer id;
    private Long value;
    private LocalDateTime offerDateTime;
    private User user;
    private Product product;
    private Address address_delivery;

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
