package com.grp3.bid;

import com.grp3.bid.DTO.UserWithAddressDTO;
import com.grp3.bid.Mapper.UserWithAddressMapper;
import com.grp3.bid.entities.Address;
import com.grp3.bid.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BidApplicationTests {

    @Autowired
    UserWithAddressMapper userWithAddressMapper;

    @Test
    void contextLoads() {
        Address address = new Address();
        address.setCity_name("city");
        address.setNb_street(0);
        address.setZip_code("35000");
        address.setStreet_name("street");
        address.setState_name("state");
        User user = new User();
        user.setUser_address(address);
        user.setEmail("mail");
        user.setPassword("passwd");
        user.setAccountWallet(100f);
        user.setRoles("");
        user.setPseudo("pseudo");
        user.setFirstName("firstname");
        user.setLastName("lastname");
        user.setPhone_number("0600000000");
        UserWithAddressDTO userDTO = userWithAddressMapper.toDTO(user);
        System.out.println(userDTO);
        System.out.println(userWithAddressMapper.toUser(userDTO));
    }

}
