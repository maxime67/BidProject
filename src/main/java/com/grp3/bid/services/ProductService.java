package com.grp3.bid.services;

import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import com.grp3.bid.repositories.ProductDAO;
import com.grp3.bid.repositories.ProductDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements ProductServiceInterface{
    @Autowired
    ProductDAOInterface productDAO;
    @Override
    public Product getProductByid(Integer id) {
        return productDAO.getProductByid(id);
    }

    @Override
    public int insertProduct(Product product) {
        return productDAO.insertProduct(product);
    }

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public boolean updateProduct(Integer id, Product product) {
        return productDAO.updateProduct(id,product);
    }

    @Override
    public List<Product> getProductListByIdSeller(User user) {
        return productDAO.getProductListByIdSeller(user);
    }

    @Override
    public List<Product> getByIdCategory(Integer idCategory) {
        return productDAO.getByIdCategory(idCategory);
    }

    @Override
    public List<Product> getEndedAuctionWithoutBuyer() {
        return productDAO.getEndedAuctionWithoutBuyer();
    }

    @Override
    public boolean updateBuyer(Product product, User buyer) {
        return productDAO.updateBuyer(product, buyer);
    }

}
