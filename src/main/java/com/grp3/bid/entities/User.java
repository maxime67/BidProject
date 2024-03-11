package com.grp3.bid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String pseudo;
    private String firstName;
    private String lastName;
    private String email;
    private String phone_number;
    private String roles;
    private Float accountWallet;
    private String password;
    private Address user_address;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", pseudo='").append(pseudo).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone_number='").append(phone_number).append('\'');
        sb.append(", roles='").append(roles).append('\'');
        sb.append(", accountWallet=").append(accountWallet);
        sb.append(", password='").append(password).append('\'');
        sb.append(", user_address_id=").append(user_address);
        sb.append('}');
        return sb.toString();
    }
}
