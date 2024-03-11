package com.grp3.bid.services;

import com.grp3.bid.entities.Address;
import com.grp3.bid.entities.Offer;

import java.util.List;

public interface AddressServiceInterface {
    Address getAddressByid(Integer id);
    boolean insertAddress(Address address);
    List<Address> getAll();
    boolean updateAddress(Integer id, Address address);

}
