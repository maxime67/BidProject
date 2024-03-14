package com.grp3.bid.repositories;

import com.grp3.bid.entities.*;
import com.grp3.bid.services.AddressServiceInterface;
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
public class UserDAO implements UserDAOInterface {
    @Autowired
    private AddressServiceInterface addressService;
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final String getUserByid = "SELECT * FROM USER_APP WHERE id_user_app = :id;";
    private final String getUserByPseudo = "SELECT * FROM USER_APP WHERE pseudo = :pseudo;";
    private final String getUserByEmail = "SELECT * FROM USER_APP WHERE email = :email;";
    private final String getByPseudo = "SELECT * FROM USER_APP WHERE pseudo = :pseudo;";
    private final String getAll = "SELECT * FROM USER_APP;";
    private final String insertUser = "INSERT INTO USER_APP (pseudo, firstname,lastname,email,phone_number,password, role_user, accountWallet, id_address) VALUES (:pseudo,:firstname,:lastname,:email,:phone_number,:password, :role_user,:accountWallet, :id_address);";
    private final String deleteUser = "DELETE FROM USER_APP WHERE id_user_app = :id_user_app";
    private final String updateAccountWallet = "UPDATE FROM USER_APP SET a";

    @Override
    public User getUserById(Integer id) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(getUserByid, sqlParameterSource, new UserRowMapper());
    }

    @Override
    public User getUserBydEmail(String email) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("email", email);
        return jdbcTemplate.queryForObject(getUserByEmail, sqlParameterSource, new UserRowMapper());
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query(getAll, new UserRowMapper());
    }

    @Override
    public int InsertUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int id_address = (addressService.insertAddress(user.getUserAddress()));
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("pseudo", user.getPseudo());
        sqlParameterSource.addValue("firstname", user.getFirstName());
        sqlParameterSource.addValue("lastname", user.getLastName());
        sqlParameterSource.addValue("email", user.getEmail());
        sqlParameterSource.addValue("password", user.getPassword());
        sqlParameterSource.addValue("phone_number", user.getPhoneNumber());
        sqlParameterSource.addValue("role_user", user.getRoles());
        sqlParameterSource.addValue("accountWallet", user.getAccountWallet());
        sqlParameterSource.addValue("id_address", id_address);
        return jdbcTemplate.update(insertUser, sqlParameterSource, keyHolder) == 1 ? keyHolder.getKey().intValue() : -1;
    }

    @Override
    public boolean updateUser(Integer id, User user) {
        return false;
    }
    @Override
    public boolean updateAccountWallet(Integer id, User user) {
       MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
       sqlParameterSource.addValue("account", user.getAccountWallet());
        return jdbcTemplate.update(updateAccountWallet, sqlParameterSource) == 1;
    }

    @Override
    public User findByPseudo(String pseudo) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("pseudo", pseudo);
        return jdbcTemplate.queryForObject(getByPseudo, sqlParameterSource, new UserRowMapper());
    }

    @Override
    public boolean deleteUser(User user) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_user_app", user.getId());
        return jdbcTemplate.update(deleteUser, sqlParameterSource) != 0;
    }

    @Override
    public User getUserByPseudo(String pseudo) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("pseudo", pseudo);
        return jdbcTemplate.queryForObject(getUserByPseudo, sqlParameterSource, new UserRowMapper());
    }

    public class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User u = new User();
            u.setId(rs.getInt("id_user_app"));
            u.setPseudo(rs.getString("pseudo"));
            u.setFirstName(rs.getString("firstname"));
            u.setLastName(rs.getString("lastname"));
            u.setEmail(rs.getString("email"));
            u.setPhoneNumber(rs.getString("phone_number"));
            u.setPassword(rs.getString("password"));
            u.setRoles(rs.getString("role_user"));
            u.setAccountWallet(rs.getFloat("accountWallet"));
            u.setUserAddress(addressService.getAddressByid(rs.getInt("id_address")));
            return u;
        }
    }
}
