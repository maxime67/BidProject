package com.grp3.bid;

import com.grp3.bid.entities.Address;
import com.grp3.bid.entities.Offer;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class BidApplication {
    @Autowired
    public UserServiceInterface userService;
    @Autowired
    public ProductServiceInterface serviceProduct;
    @Autowired
    public OfferServiceInterface offerService;
    @Autowired
    public AddressServiceInterface addressService;


    @PostConstruct
    public void init() {
        Address addr1 = new Address(1L,"street_name","state_name","city_name",12,"29300");
        Address addr2 = new Address(2L,"street_name","state_name","city_name",12,"29300");

        User ap = new User("pseudo1","firstname1","lastName1","email1","phone_number1","ADMIN,USER",1F,"password1",new Address(2L,"street_name","state_name","city_name",12,"29300"));
        System.out.println(ap);

        addressService.insertAddress(addr1);
        addressService.insertAddress(addr2);
        addressService.insertAddress(addr2);

        userService.insertUser(new User("pseudo1","firstname1","lastName1","email1","phone_number1","ADMIN,USER",1F,"password1",addr2));
        userService.insertUser(new User("pseudo2","firstname2","lastName2","email2","phone_number2","ADMIN,USER",2F,"password2",addr2));
        userService.insertUser(new User("pseudo3","firstname3","lastName3","email3","phone_number3","ADMIN,USER",3F,"password3",addr1));

        serviceProduct.insertProduct(new Product("nameProd","aaaa",101L,"aaa", LocalDate.now(), LocalDate.of(2024,03, 25), userService.getUserByid(1)));
        serviceProduct.insertProduct(new Product("nameProd","aaaa",101L,"aaa", LocalDate.now(),LocalDate.of(2024,03, 25),  userService.getUserByid(1)));
        serviceProduct.insertProduct(new Product("nameProd","aaaa",101L,"aaa", LocalDate.now(), LocalDate.of(2024,03, 25),userService.getUserByid(1)));
        serviceProduct.insertProduct(new Product("nameProd","aaaa",101L,"aaa", LocalDate.now(), LocalDate.of(2024,03, 25),userService.getUserByid(1)));

        offerService.insertOffer(new Offer(15L,LocalDateTime.now(), userService.getUserByid(1), serviceProduct.getProductByid(1), addr2));

        userService.getAll().forEach(System.out::println);
        userService.deleteUser(userService.getUserByid(1));
        userService.getAll().forEach(System.out::println);

    }

    public static void main(String[] args) {
        SpringApplication.run(BidApplication.class, args);
    }

}
