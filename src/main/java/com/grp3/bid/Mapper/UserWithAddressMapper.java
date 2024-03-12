package com.grp3.bid.Mapper;

import com.grp3.bid.DTO.UserWithAddressDTO;
import com.grp3.bid.entities.Address;
import com.grp3.bid.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserWithAddressMapper {
    public UserWithAddressDTO toDTO (User user)
    {
        return new UserWithAddressDTO(
                user.getPseudo(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone_number(),
                user.getPassword(),
                user.getUser_address().getStreet_name(),
                user.getUser_address().getState_name(),
                user.getUser_address().getCity_name(),
                user.getUser_address().getNb_street(),
                user.getUser_address().getZip_code()
        );
    }

    public User toUser(UserWithAddressDTO userWithAddressDTO)
    {
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
        user.setPhone_number(userWithAddressDTO.getPhone_number());
        user.setUser_address(address);
        return user;
    }
}
