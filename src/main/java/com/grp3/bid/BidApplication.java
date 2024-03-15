package com.grp3.bid;

import com.grp3.bid.entities.*;
import com.grp3.bid.services.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.parameters.P;

import java.io.File;
import java.time.LocalDateTime;

@SpringBootApplication
public class BidApplication {
    @Autowired
    public UserServiceInterface userService;
    @Autowired
    public ProductServiceInterface productService;
    @Autowired
    public OfferServiceInterface offerService;
    @Autowired
    public AddressServiceInterface addressService;
    @Autowired
    public CategoryServiceInterface categoryService;


    @PostConstruct
    public void init() {
        Address addr1 = new Address(1L,"street_name","state_name","city_name",12,"29300");
        Address addr2 = new Address(2L,"street_name","state_name","city_name",12,"29300");

        User ap = new User("pseudo1","firstname1","lastName1","email1","phone_number1","ADMIN,USER",1F,"password1",new Address(2L,"street_name","state_name","city_name",12,"29300"));
        System.out.println(ap);

        Category cat1 = new Category(1L, "Jouets");
        Category cat2 = new Category(2L, "Mobilier");
        categoryService.insertCategory(cat1);
        categoryService.insertCategory(cat2);

        addressService.insertAddress(addr1);
        addressService.insertAddress(addr2);
        addressService.insertAddress(addr2);

        userService.insertUser(new User("pseudo1","firstname1","lastName1","email1","phone_number1","ADMIN,USER",1F,"password1",addr2));
        userService.insertUser(new User("pseudo2","firstname2","lastName2","email2","phone_number2","ADMIN,USER",2F,"password2",addr2));
        userService.insertUser(new User("pseudo3","firstname3","lastName3","email3","phone_number3","ADMIN,USER",3F,"password3",addr1));

        productService.insertProduct(new Product("nameProd","aaaa",101L,"couteau.jpg",  LocalDateTime.of(2024, 3, 23, 12, 00), LocalDateTime.of(2024, 3, 25, 12, 00), userService.getUserByid(1), cat1));
        productService.insertProduct(new Product("nameProd","aaaa",101L,"tooth.png",  LocalDateTime.of(2024, 3, 23, 12, 00), LocalDateTime.of(2024, 3, 25, 12, 00), userService.getUserByid(1),cat2));

//        offerService.insertOffer(new Offer(15F,LocalDateTime.now(), userService.getUserByid(1), productService.getProductByid(1), addr2));

        userService.getAll().forEach(System.out::println);
        productService.getAll().forEach(System.out::println);

        categoryService.getAll().forEach(System.out::println);
        
        categoryService.getAll().forEach(System.out::println);


    }

    public static void main(String[] args) {
        SpringApplication.run(BidApplication.class, args);
    }

}
