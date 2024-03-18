package com.grp3.bid.services;

import com.grp3.bid.entities.*;

import java.util.List;

public interface ProductServiceInterface {
    Product getProductByid(Integer id);
    int insertProduct(Product product);
    List<Product> getAll();
    boolean updateProduct(Integer id, Product product);

    List<Product> getProductListByIdSeller(User user);

    List<Product> getByIdCategory(Integer idCategory);
    List<Product> getEndedAuctionWithoutBuyer();
    boolean updateBuyer(Product product, User buyer);

    List<Product> getAllActif();
}
