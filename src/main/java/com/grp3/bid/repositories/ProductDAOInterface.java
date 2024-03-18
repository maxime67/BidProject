package com.grp3.bid.repositories;

import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;

import java.util.List;

public interface ProductDAOInterface {
    Product getProductByid(Integer id);
    List<Product> getAll();
    int insertProduct(Product product);
    boolean updateProduct(Integer id, Product product);
    List<Product> getByIdCategory(Integer idCategory);
    List<Product> getEndedAuctionWithoutBuyer();
    boolean updateBuyer(Product product, User buyer);
    List<Product> getProductListByIdSeller(User user);
}
