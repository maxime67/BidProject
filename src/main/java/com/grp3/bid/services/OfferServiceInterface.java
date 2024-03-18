package com.grp3.bid.services;

import com.grp3.bid.entities.Offer;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;

import java.util.List;

public interface OfferServiceInterface {
    Offer getOfferByid(Integer id);
    int insertOffer(Offer offer);
    List<Offer> getAll();
    boolean updateOffer(Integer id, Offer offer);
    Offer getActualMaxOffer(Integer idProduct);
    boolean isOfferExistOnProduct(Integer idProduct);
    List<Offer> getOfferByUser(User user);
    List<Offer> getAllOffersByproduct(Product product);
}
