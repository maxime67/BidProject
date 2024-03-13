package com.grp3.bid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Long id_address;
    private String street_name;
    private String state_name;
    private String city_name;
    private Integer nb_street;
    private String zip_code;

    public Address(String street_name, String state_name, String city_name, Integer nb_street, String zip_code) {
        this.street_name = street_name;
        this.state_name = state_name;
        this.city_name = city_name;
        this.nb_street = nb_street;
        this.zip_code = zip_code;
    }

}
