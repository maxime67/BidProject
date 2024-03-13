package com.grp3.bid.repositories;

import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class ProductDAO implements ProductDAOInterface {

    private final String getProductByid = "SELECT * FROM PRODUCT WHERE id_PRODUCT = :id_product";
    private final String getAll = "SELECT * FROM PRODUCT";
    private final String insertProduct = "INSERT INTO PRODUCT (name_product,description,starting_value,path_to_image,start_date, end_date, category, id_seller) VALUES (:name_product,:description,:starting_value,:path_to_image,:start_date,:end_date,:category,:id_seller)";
    @Autowired
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    UserDAOInterface userDAOInterface;

    public ProductDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product getProductByid(Integer id) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_product", id);
        return jdbcTemplate.queryForObject(getProductByid, sqlParameterSource, new ProductRowMapper());
    }

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query(getAll, new ProductRowMapper());
    }

    @Override
    public void insertProduct(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name_product", product.getName());
        sqlParameterSource.addValue("description", product.getDescription());
        sqlParameterSource.addValue("starting_value", product.getStartingValue());
        sqlParameterSource.addValue("path_to_image", product.getPathToImg());
        sqlParameterSource.addValue("start_date", Timestamp.valueOf(product.getStartDate()));
        sqlParameterSource.addValue("end_date", Timestamp.valueOf(product.getEndDate()));
        sqlParameterSource.addValue("category", product.getCategory());
        sqlParameterSource.addValue("id_seller", product.getSeller().getId());


        //jdbcTeamlplte.update return numbers of affected lines
        if (keyHolder != null && keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            product.setId(keyHolder.getKey().intValue());
        }
        jdbcTemplate.update(insertProduct, sqlParameterSource,keyHolder);
    }

    @Override
    public boolean updateProduct(Integer id, Product product) {
        return false;
    }

    public class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product p = new Product();
            p.setId(rs.getInt("id_product"));
            p.setName(rs.getString("name_product"));
            p.setDescription(rs.getString("description"));
            p.setStartingValue(rs.getDouble("starting_value"));
            p.setPathToImg(rs.getString("path_to_image"));

           /* Date date = rs.getDate("start_date");
            p.setStartDate(date.toLocalDateTime());*/

            Timestamp timestampEnd = rs.getTimestamp("end_date");
            p.setStartDate(timestampEnd.toLocalDateTime());


            p.setCategory(rs.getString("category"));
            User userSeller = userDAOInterface.getUserById(rs.getInt("id_seller"));
            p.setSeller(userSeller);
            return p;
        }
    }
}
