package com.grp3.bid;

import com.grp3.bid.entities.Address;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.services.ProductService;
import com.grp3.bid.services.ProductServiceInterface;
import com.grp3.bid.services.UserServiceInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.parameters.P;

import java.time.LocalDateTime;

@SpringBootApplication
public class BidApplication {
    @Autowired
    public UserServiceInterface service;
    @Autowired
    public ProductServiceInterface serviceproduct;


    @PostConstruct
    public void init() {
        service.insertUser(new User("pseudo1","firstname1","lastName1","email1","phone_number1","ADMIN,USER",1F,"password1",new Address(2L,"street_name","state_name","city_name",12,"29300")));
        service.insertUser(new User("pseudo2","firstname2","lastName2","email2","phone_number2","ADMIN,USER",2F,"password2",new Address(3L,"street_name","state_name","city_name",12,"29300")));
        service.insertUser(new User("pseudo3","firstname3","lastName3","email3","phone_number3","ADMIN,USER",3F,"password3",new Address(4L,"street_name","state_name","city_name",12,"29300")));
        serviceproduct.insertProduct(new Product("nameProd","aaaa",101L,"aaa", LocalDateTime.now(), service.getUserByid(1)));
        service.getAll().forEach(System.out::println);
    }

    public static void main(String[] args) {
        SpringApplication.run(BidApplication.class, args);
    }

}
