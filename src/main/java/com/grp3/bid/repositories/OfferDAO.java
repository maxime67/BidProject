package com.grp3.bid.repositories;

import com.grp3.bid.entities.*;

import java.util.List;

public interface OfferDAO {
    Offer getOfferById(Integer id);
    List<Offer> getAll();
    boolean insertOffer(Offer offer);
    boolean updateOffer(Integer id, Offer offer);


}
