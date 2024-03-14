package com.grp3.bid.Mapper;

import com.grp3.bid.DTO.UserWithAddressDTO;
import com.grp3.bid.entities.Address;
import com.grp3.bid.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserWithAddressMapper {
    public UserWithAddressDTO toDTO(User user) {
        return new UserWithAddressDTO(
                user.getPseudo(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPassword(),
                user.getUserAddress().getStreet_name(),
                user.getUserAddress().getState_name(),
                user.getUserAddress().getCity_name(),
                user.getUserAddress().getNb_street(),
                user.getUserAddress().getZip_code()
        );
    }

    public User toUser(UserWithAddressDTO userWithAddressDTO) {
        Address address = new Address();
        address.setCity_name(userWithAddressDTO.getCity_name());
        address.setNb_street(userWithAddressDTO.getNb_street());
        address.setZip_code(userWithAddressDTO.getZip_code());
        address.setStreet_name(userWithAddressDTO.getStreet_name());
        address.setState_name(userWithAddressDTO.getState_name());

        User user = new User();
        user.setPseudo(userWithAddressDTO.getPseudo());
        user.setEmail(userWithAddressDTO.getEmail());
        user.setPassword(userWithAddressDTO.getPassword());
        user.setFirstName(userWithAddressDTO.getFirstName());
        user.setLastName(userWithAddressDTO.getLastName());
        user.setPhoneNumber(userWithAddressDTO.getPhone_number());
        user.setUserAddress(address);
        return user;
    }
}
