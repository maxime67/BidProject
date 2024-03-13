package com.grp3.bid.repositories;


import com.grp3.bid.entities.Offer;
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
import java.util.Objects;


@Repository
public class OfferDAO implements OfferDAOInterface {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private ProductDAOInterface productDAO;
    @Autowired
    private UserDAOInterface userDAO;
    private final String getOfferById = "SELECT * FROM OFFER WHERE id_offer = :id;";
    private final String getAll = "SELECT * FROM OFFER;";

    private final String insertOffer = "INSERT INTO OFFER (value_offer,date_offer,id_product,id_user_app) VALUES (:value_offer,:offer_datetime,:id_product,:id_user_app);";
    private final String updateOffer = "UPDATE OFFER SET value=?,offer_datetime=?,id_user=?,id_product=? WHERE id_offer = ?;";
    private final String getActualMaxOffer = "SELECT TOP 1 * FROM OFFER WHERE id_product = :id_product ORDER BY value_offer";

    public OfferDAO(NamedParameterJdbcTemplate jdbcTemplate, ProductDAOInterface productDAO, UserDAOInterface userDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.productDAO = productDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Offer getOfferById(Integer id) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id", 1);
        return jdbcTemplate.queryForObject(getOfferById, sqlParameterSource, new OfferRowMapper());
    }

    @Override
    public List<Offer> getAll() {
        return jdbcTemplate.query(getAll, new OfferRowMapper());
    }

    @Override
    public int insertOffer(Offer offer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("value_offer", offer.getValue());
        sqlParameterSource.addValue("offer_datetime", offer.getOfferDateTime());
        sqlParameterSource.addValue("id_product", offer.getProduct().getId());
        sqlParameterSource.addValue("id_user_app", offer.getUser().getId());
        //jdbcTeamlplte.update return numbers of affected lines
        return jdbcTemplate.update(insertOffer, sqlParameterSource, keyHolder) == 1 ? keyHolder.getKey().intValue() : -1;
    }

    @Override
    public boolean updateOffer(Integer id, Offer offer) {
        return false;
    }
    @Override
    public Offer getActualMaxOffer(Integer idProduct) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_product", idProduct);
        return jdbcTemplate.queryForObject(getActualMaxOffer, sqlParameterSource, new OfferRowMapper());
    }

    public class OfferRowMapper implements RowMapper<Offer> {

        @Override
        public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Offer o = new Offer();
            o.setId(rs.getInt("id_offer"));
            o.setValue(rs.getFloat("value_offer"));
            o.setOfferDateTime(rs.getTimestamp("date_offer").toLocalDateTime());
            o.setUser(userDAO.getUserById(rs.getInt("id_user_app")));
            o.setProduct(productDAO.getProductByid(rs.getInt("id_product")));
            return o;
        }
    }
}
