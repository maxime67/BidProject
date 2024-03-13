package com.grp3.bid.repositories;

import com.grp3.bid.entities.*;

import java.util.List;

public interface AddressDAOInterface {
    Address getAddressById(Integer id);
    List<Address> getAll();
    int insertAddress(Address address);
    boolean updateAddress(Integer id, Address address);


}
