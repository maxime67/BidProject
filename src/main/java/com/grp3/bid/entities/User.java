package com.grp3.bid.entities;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @NotNull
    private Integer id;
    @NotNull
    private String pseudo;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String roles;
    @NotNull
    private Float accountWallet;
    @NotNull
    private String password;
    @NotNull
    private Address userAddress;

    public User(String pseudo, String firstName, String lastName, String email, String phoneNumber, String roles, Float accountWallet, String password, Address userAddress) {
        this.pseudo = pseudo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.accountWallet = accountWallet;
        this.password = password;
        this.userAddress = userAddress;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", pseudo='").append(pseudo).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone_number='").append(phoneNumber).append('\'');
        sb.append(", roles='").append(roles).append('\'');
        sb.append(", accountWallet=").append(accountWallet);
        sb.append(", password='").append(password).append('\'');
        sb.append(", user_address_id=").append(userAddress);
        sb.append('}');
        return sb.toString();
    }
}
