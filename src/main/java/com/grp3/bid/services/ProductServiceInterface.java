package com.grp3.bid.services;

import com.grp3.bid.entities.*;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface ProductServiceInterface {
    Product getProductByid(Integer id);
    boolean insertProduct(Product product);
    List<Product> getAll();
    boolean updateProduct(Integer id, Product product);
    Product getLastOffer(Product product);
}
