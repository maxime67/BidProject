package com.grp3.bid.repositories;

import com.grp3.bid.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdresseDAOImpl implements AddresseDAO {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String getAddressByid = "SELECT * FROM ADDRESS WHERE id_address = :id_address";
    private final String insertAddress = "INSERT INTO ADDRESS (street_name,city_name,state_name,nb_street,zip_code) VALUES (:street_name,:city_name,:state_name,:nb_street,:zip_code)";
    private final String getAll = "SELECT * from ADDRESS";
    private final String updateAddress = "UPDATE ADDRESS SET street_name = :street_name, city_name = :city_name, state_name = :state_name, nb_street = :nb_street, zip_code = :zip_code WHERE id_address = :id_address";

    @Override
    public Address getAddressByid(Integer id) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_address", id);
        return jdbcTemplate.queryForObject(getAddressByid, sqlParameterSource, new BeanPropertyRowMapper<>(Address.class));
    }

    @Override
    public boolean insertAddress(Address address) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("street_name", address.getStreet_name());
        sqlParameterSource.addValue("city_name", address.getCity_name());
        sqlParameterSource.addValue("state_name", address.getState_name());
        sqlParameterSource.addValue("nb_street", address.getNb_street());
        sqlParameterSource.addValue("zip_code", address.getZip_code());
        return jdbcTemplate.update(insertAddress, sqlParameterSource) == 1;

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