package com.grp3.bid.Mapper;

import com.grp3.bid.DTO.EditUserDTO;
import com.grp3.bid.DTO.EditUserDTO;
import com.grp3.bid.Utils.AuthenticationFacadeInterface;
import com.grp3.bid.entities.Address;
import com.grp3.bid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditUserMapper {

    @Autowired
    AuthenticationFacadeInterface authenticationFacade;
    public EditUserDTO toDTO (User user)
    {
        return new EditUserDTO(
                user.getId(),
                user.getPseudo(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getUserAddress().getId_address(),
                user.getUserAddress().getStreet_name(),
                user.getUserAddress().getState_name(),
                user.getUserAddress().getCity_name(),
                user.getUserAddress().getNb_street(),
                user.getUserAddress().getZip_code()
        );
    }

    public User toUser(EditUserDTO editUserDTO)
    {
        User user = authenticationFacade.getConnectedUser();
        user.setPseudo(editUserDTO.getPseudo());
        user.setEmail(editUserDTO.getEmail());
        user.setPassword(editUserDTO.getPassword());
        user.setFirstName(editUserDTO.getFirstName());
        user.setLastName(editUserDTO.getLastName());
        user.setPhoneNumber(editUserDTO.getPhone_number());
        user.getUserAddress().setCity_name(editUserDTO.getCity_name());
        user.getUserAddress().setNb_street(editUserDTO.getNb_street());
        user.getUserAddress().setZip_code(editUserDTO.getZip_code());
        user.getUserAddress().setStreet_name(editUserDTO.getStreet_name());
        user.getUserAddress().setState_name(editUserDTO.getState_name());
        return user;
    }
}
