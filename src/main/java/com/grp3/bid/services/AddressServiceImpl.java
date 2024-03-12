package com.grp3.bid.services;

import com.grp3.bid.entities.Address;
import com.grp3.bid.repositories.AddresseDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddresseDAOInterface addresseDAO;

    @Override
    public Address getAddressByid(Integer id) {
        return null;
    }

    @Override
    public boolean insertAddress(Address address) {
        return false;
    }

    @Override
    public List<Address> getAll() {
        return null;
    }

    @Override
    public boolean updateAddress(Integer id, Address address) {
        return false;
    }
}
