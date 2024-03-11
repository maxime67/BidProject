package com.grp3.bid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class address {
    private Long id_address;
    private String street_name;
    private String state_name;
    private String city_name;
    private Integer nb_street;
    private String zip_code;

}
