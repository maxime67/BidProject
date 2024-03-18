package com.grp3.bid.DTO;

import com.grp3.bid.constraints.UniqueEmailConstraint;
import com.grp3.bid.constraints.UniquePseudoConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserDTO {
    private int id;
    @NotBlank(message = "Le pseudo est obligatoire")
    @UniquePseudoConstraint
    @Pattern(regexp="^[a-zA-Z0-9_]*$", message = "Le pseudo ne doit contenir que des caractères alphanumériques")
    private String pseudo;
    @NotBlank(message = "Le nom est obligatoire")
    private String firstName;
    @NotBlank(message = "Le prénom est obligatoire")
    private String lastName;
    @NotBlank(message = "Le mail est obligatoire")
    @UniqueEmailConstraint
    @Email
    private String email;
    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    private String phone_number;
    @Pattern(regexp = "(^$)|(^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$)", message = "Le mot de passe doit contenir au moins 8 caractères, dont une lettre, un chiffre et un caractère spécial ")
    private String password = "";
    @Pattern(regexp = "^.{1,255}$" , message = "La description est limité à 255 charactères")
    private String description;
    private long id_address;
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

    public EditUserDTO(int id, String pseudo, String firstName, String lastName, String email, String phone_number,String description, long id_address, String street_name, String state_name, String city_name, Integer nb_street, String zip_code) {
        this.id = id;
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone_number = phone_number;
        this.description = description;
        this.street_name = street_name;
        this.state_name = state_name;
        this.city_name = city_name;
        this.nb_street = nb_street;
        this.zip_code = zip_code;
    }
}
