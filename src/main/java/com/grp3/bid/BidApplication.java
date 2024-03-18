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
    @Autowired
    public DeleteUserInterface deleteUser;
    @Autowired
    public AccountServiceInterface accountService;


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

        userService.insertUser(new User("pseudo1","Djibril1","Laporte1","1Djibril.laporte@gmail.com","0298965678","ADMIN,USER",10000F,"password1",addr2));
        userService.insertUser(new User("pseudo2","Djibril2","Laporte2","2Djibril.laporte@gmail.com","0298965678","ADMIN,USER",10000F,"password2",addr2));
        userService.insertUser(new User("pseudo3","Djibril3","Laporte3","3Djibril.laporte@gmail.com","0298965678","ADMIN,USER",10000F,"password3",addr1));
        accountService.addToAccount(userService.getUserByid(1), 900F);
        accountService.addToAccount(userService.getUserByid(2), 900F);
        accountService.addToAccount(userService.getUserByid(3), 900F);

        productService.insertProduct(new Product("Couteau","Jolie couteau du XIIème siècle",1F,"couteau.jpg",  LocalDateTime.now(),  LocalDateTime.now().plusSeconds(30), userService.getUserByid(1), cat1));
        productService.insertProduct(new Product("Voiture","Mégane RS Trophy à vendre",1F,"tooth.png",   LocalDateTime.now(),  LocalDateTime.now().plusSeconds(30), userService.getUserByid(1),cat2));
        productService.insertProduct(new Product("Bateau","Hobbie 16 à vendre",1F,"tooth.png",   LocalDateTime.now(), LocalDateTime.now().plusSeconds(30), userService.getUserByid(1), cat2));
        productService.insertProduct(new Product("Bateau","Hobbie 16 à vendre",1F,"tooth.png",   LocalDateTime.now(), LocalDateTime.now().plusDays(30), userService.getUserByid(1), cat2));
        productService.insertProduct(new Product("Bateau","Hobbie 16 à vendre",1F,"tooth.png",   LocalDateTime.now(), LocalDateTime.now().plusDays(30), userService.getUserByid(1), cat2));

//        offerService.insertOffer(new Offer(100F,LocalDateTime.now(), userService.getUserByid(3), productService.getProductByid(1), addr2));
//        offerService.insertOffer(new Offer(100F, LocalDateTime.now(), userService.getUserByid(3), productService.getProductByid(2), addr2));
//        offerService.insertOffer(new Offer(100F, LocalDateTime.now(), userService.getUserByid(3), productService.getProductByid(3), addr2));
    }
    public static void main(String[] args) {
        SpringApplication.run(BidApplication.class, args);
    }

}
