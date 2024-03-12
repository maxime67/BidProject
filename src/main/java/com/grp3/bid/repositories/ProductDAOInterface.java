package com.grp3.bid.repositories;

import com.grp3.bid.entities.Product;

import java.util.List;

public interface ProductDAOInterface {
    Product getProductByid(Integer id);
    List<Product> getAll();
    int insertProduct(Product product);
    boolean updateProduct(Integer id, Product product);
}
