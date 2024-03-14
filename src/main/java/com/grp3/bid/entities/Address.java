package com.grp3.bid.entities;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {
    @NotNull
    private Long id_address;
    @Nullable
    private String street_name;
    @NotNull
    private String state_name;
    @NotNull
    private String city_name;
    @Nullable
    private Integer nb_street;
    @Nullable
    private String zip_code;

    public Address(String street_name, String state_name, String city_name, Integer nb_street, String zip_code) {
        this.street_name = street_name;
        this.state_name = state_name;
        this.city_name = city_name;
        this.nb_street = nb_street;
        this.zip_code = zip_code;
    }

}
