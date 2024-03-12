package com.grp3.bid.DTO;

import com.grp3.bid.constraints.UniquePseudoConstraint;
import com.grp3.bid.entities.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWithAddressDTO {
    @NotBlank(message = "Le pseudo est obligatoire")
    @UniquePseudoConstraint
    private String pseudo;
    @NotBlank(message = "Le nom est obligatoire")
    private String firstName;
    @NotBlank(message = "Le prénom est obligatoire")
    private String lastName;
    @NotBlank(message = "Le mail est obligatoire")
    private String email;
    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    private String phone_number;
    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
    @NotBlank(message = "La rue est obligatoire")
    private String street_name;
    @NotBlank(message = "La région est obligatoire")
    private String state_name;
    @NotBlank(message = "La ville est obligatoire")
    private String city_name;
    @NotNull(message = "Le numéro de rue est obligatoire")
    private Integer nb_street;
    @NotBlank(message = "Le code postal est obligatoire")
    private String zip_code;


}
