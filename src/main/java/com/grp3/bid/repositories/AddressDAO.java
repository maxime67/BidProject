package com.grp3.bid.repositories;


import com.grp3.bid.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class AddressDAO implements AddressDAOInterface {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private final String getAddressById = "SELECT * FROM ADDRESS WHERE id_address = :id;";
    private final String getAll = "SELECT * FROM ADDRESS";

    private final String insertAddress = "INSERT INTO ADDRESS (street_name, city_name, state_name, nb_street, zip_code) VALUES (:street_name, :city_name, :state_name, :nb_street, :zip_code);";
    private final String updateAddress = "UPDATE ADDRESS SET street_name=:street_name, city_name=:city_name, state_name=:state_name, nb_street=:nb_street, zip_code=:zip_code;";

    public AddressDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Address getAddressById(Integer id) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(getAddressById, sqlParameterSource, new AddressRowMapper());
    }

    @Override
    public List<Address> getAll() {
        return jdbcTemplate.query(getAll, new AddressRowMapper());
    }

    @Override
    public int insertAddress(Address address) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("street_name", address.getStreet_name());
        sqlParameterSource.addValue("city_name", address.getCity_name());
        sqlParameterSource.addValue("state_name", address.getState_name());
        sqlParameterSource.addValue("nb_street", address.getNb_street());
        sqlParameterSource.addValue("zip_code", address.getZip_code());
        //jdbcTeamlplte.update return numbers of affected lines
        return jdbcTemplate.update(insertAddress, sqlParameterSource, keyHolder) == 1 ? keyHolder.getKey().intValue() : -1;
    }

    @Override
    public boolean updateAddress(Integer id, Address address) {
        return false;
    }

    public class AddressRowMapper implements RowMapper<Address> {

        @Override
        public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
            Address a = new Address();
            a.setId_address(rs.getLong("id_address"));
            a.setStreet_name(rs.getString("street_name"));
            a.setCity_name(rs.getString("city_name"));
            a.setState_name(rs.getString("state_name"));
            a.setNb_street(rs.getInt("nb_street"));
            a.setZip_code(rs.getString("zip_code"));
            return a;
        }
    }
}
