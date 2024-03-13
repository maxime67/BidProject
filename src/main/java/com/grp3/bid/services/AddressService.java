package com.grp3.bid.services;

import com.grp3.bid.repositories.*;
import com.grp3.bid.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements AddressServiceInterface{
    @Autowired
    AddressDAOInterface AddressDAO;

    @Override
    public Address getAddressByid(Integer id) {
        if(AddressDAO.getAddressById(id) != null){
            return AddressDAO.getAddressById(id);
        }
        return null;
    }

    @Override
    public int insertAddress(Address Address) {
        return AddressDAO.insertAddress(Address);
    }

    @Override
    public List<Address> getAll() {
        return AddressDAO.getAll();
    }

    @Override
    public boolean updateAddress(Integer id, Address address) {
        return AddressDAO.updateAddress(id,address);
    }

}
