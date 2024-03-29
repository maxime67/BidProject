package com.grp3.bid.repositories;

import com.grp3.bid.entities.*;
import com.grp3.bid.services.AddressServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    private final String insertUser = "INSERT INTO USER_APP (pseudo, firstname,lastname,email,phone_number,password, role_user, accountWallet, id_address, description, nb_sales) VALUES (:pseudo,:firstname,:lastname,:email,:phone_number,:password, :role_user,:accountWallet,:id_address,:description, :nb_sales);";
    private final String deleteUser = "DELETE FROM USER_APP WHERE id_user_app = :id_user_app";
    private final String updateAccountWallet = "UPDATE USER_APP SET accountWallet = :accountWallet WHERE id_user_app = :id";
    private final String UPDATE_USER_WITHOUT_PASSWORD = "UPDATE USER_APP SET pseudo = :pseudo, firstname = :firstname, lastname = :lastname, email = :email, phone_number = :phone_number, description = :description WHERE id_user_app = :id_user_app;";
    private final String UPDATE_USER_WITH_PASSWORD = "UPDATE USER_APP SET pseudo = :pseudo, firstname = :firstname, lastname = :lastname, email = :email, phone_number = :phone_number, password = :password, description = :description WHERE id_user_app = :id_user_app;";
    private final String UPDATE_USER_PASSWORD = "UPDATE USER_APP SET password = :password WHERE id_user_app = :id_user_app;";
    @Override
    public User getUserById(Integer id) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", id);
        try {
            return jdbcTemplate.queryForObject(getUserByid, sqlParameterSource, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
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
        sqlParameterSource.addValue("description", user.getDescription());
        sqlParameterSource.addValue("nb_sales", user.getNbSales());
        sqlParameterSource.addValue("accountWallet", user.getAccountWallet());
        sqlParameterSource.addValue("id_address", id_address);
        return jdbcTemplate.update(insertUser, sqlParameterSource, keyHolder) == 1 ? keyHolder.getKey().intValue() : -1;
    }

    @Override
    public boolean updateUser(User user) {
        boolean isUpdatePassword = !user.getPassword().isEmpty();
        addressService.updateAddress(user.getUserAddress());
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_user_app", user.getId());
        sqlParameterSource.addValue("pseudo", user.getPseudo());
        sqlParameterSource.addValue("firstname", user.getFirstName());
        sqlParameterSource.addValue("lastname", user.getLastName());
        sqlParameterSource.addValue("email", user.getEmail());
        sqlParameterSource.addValue("phone_number", user.getPhoneNumber());
        sqlParameterSource.addValue("description", user.getDescription());
        if (isUpdatePassword) {
            sqlParameterSource.addValue("password", user.getPassword());
        }
        return jdbcTemplate.update(isUpdatePassword ? UPDATE_USER_WITH_PASSWORD : UPDATE_USER_WITHOUT_PASSWORD, sqlParameterSource) == 1 ;
    }
    @Override
    public boolean updateAccountWallet(User user) {
       MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("accountWallet", user.getAccountWallet());
        sqlParameterSource.addValue("id", user.getId());
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

    @Override
    public boolean updateUserPassword(User user, String password) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_user_app", user.getId());
        sqlParameterSource.addValue("password", password);
        return jdbcTemplate.update(UPDATE_USER_PASSWORD, sqlParameterSource) != 0;
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
            u.setNbSales(rs.getInt("nb_sales"));
            u.setDescription(rs.getString("description"));
            u.setAccountWallet(rs.getFloat("accountWallet"));
            u.setUserAddress(addressService.getAddressByid(rs.getInt("id_address")));
            return u;
        }
    }
}
