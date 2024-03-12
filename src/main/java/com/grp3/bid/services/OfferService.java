package com.grp3.bid.services;

import com.grp3.bid.repositories.*;
import com.grp3.bid.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService implements OfferServiceInterface{
    @Autowired
    OfferDAOInterface offerDAOInterface;

    @Override
    public Offer getOfferByid(Integer id) {
        if(offerDAOInterface.getOfferById(id) != null){
            return offerDAOInterface.getOfferById(id);
        }
        return null;
    }

    @Override
    public boolean insertOffer(Offer offer) {
        return offerDAOInterface.insertOffer(offer);
    }

    @Override
    public List<Offer> getAll() {
        return offerDAOInterface.getAll();
    }

    @Override
    public boolean updateOffer(Integer id, Offer offer) {
        return offerDAOInterface.updateOffer(id,offer);
    }

}
