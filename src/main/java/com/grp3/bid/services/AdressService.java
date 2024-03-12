package com.grp3.bid.services;

import com.grp3.bid.entities.Address;
import com.grp3.bid.repositories.AddresseDAOInterface;
import com.grp3.bid.repositories.OfferDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService implements AddressServiceInterface{
    @Autowired
    AddresseDAOInterface addresseDAO;

    @Override
    public Address getAddressByid(Integer id) {
        return addresseDAO.getAddressByid(id);
    }

    @Override
    public boolean insertAddress(Address address) {
        return addresseDAO.insertAddress(address);
    }

    @Override
    public List<Address> getAll() {
        return addresseDAO.getAll();
    }

    @Override
    public boolean updateAddress(Integer id, Address address) {
        return false;
    }
}
