package com.grp3.bid.services;

import com.grp3.bid.entities.Address;
import com.grp3.bid.repositories.AddressDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService implements AddressServiceInterface{
    @Autowired
    AddressDAOInterface addressDAO;

    @Override
    public Address getAddressByid(Integer id) {
        return addressDAO.getAddressById(id);
    }

    @Override
    public int insertAddress(Address address) {
        return addressDAO.insertAddress(address);
    }

    @Override
    public List<Address> getAll() {
        return addressDAO.getAll();
    }

    @Override
    public boolean updateAddress(Integer id, Address address) {
        return false;
    }
}
