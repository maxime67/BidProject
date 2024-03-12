package com.grp3.bid.repositories;

import com.grp3.bid.entities.Product;

import java.util.List;

public interface ProductDAO {
    Product getProductByid(Integer id);
    List<Product> getAll();
    boolean insertProduct(Product product);
    boolean updateProduct(Integer id, Product product);
}
