package com.grp3.bid.repositories;

import com.grp3.bid.entities.Address;

import java.util.List;

public interface AddresseDAOInterface {
    Address getAddressByid(Integer id);
    boolean insertAddress(Address address);
    List<Address> getAll();
    boolean updateAddress(Integer id, Address address);
}
