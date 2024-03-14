package com.grp3.bid.services;

import com.grp3.bid.entities.Offer;

import java.util.List;

public interface OfferServiceInterface {
    Offer getOfferByid(Integer id);
    int insertOffer(Offer offer);
    List<Offer> getAll();
    boolean updateOffer(Integer id, Offer offer);

    Offer getActualMaxOffer(Integer idProduct);
}
