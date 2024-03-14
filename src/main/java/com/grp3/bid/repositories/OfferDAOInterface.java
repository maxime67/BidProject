package com.grp3.bid.repositories;

import com.grp3.bid.entities.*;

import java.util.List;

public interface OfferDAOInterface {
    Offer getOfferById(Integer id);
    List<Offer> getAll();
    int insertOffer(Offer offer);
    boolean updateOffer(Integer id, Offer offer);
    Offer getActualMaxOffer(Integer idProduct);
}
