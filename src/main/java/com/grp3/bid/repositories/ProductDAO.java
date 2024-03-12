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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Repository
public class ProductDAO implements ProductDAOInterface{

    private final String getProductByid = "SELECT * FROM PRODUCT WHERE id_PRODUCT = :id_product";
    private final String getAll = "SELECT * FROM PRODUCT";
    private final String insertProduct = "INSERT INTO PRODUCT (name_product,description,starting_value,path_to_image,start_date, end_date) VALUES (:name_product,:description,:starting_value,:path_to_image, :start_date, :end_date)";
    @Autowired
    private final NamedParameterJdbcTemplate jdbcTemplate;

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
    public boolean insertProduct(Product product) {
        KeyHolder keyHolder =  new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name_product", product.getName());
        sqlParameterSource.addValue("description", product.getDescription());
        sqlParameterSource.addValue("starting_value", product.getStartingValue());
        sqlParameterSource.addValue("path_to_image", product.getPathToImg());
        sqlParameterSource.addValue("start_date", product.getStartDate());
        sqlParameterSource.addValue("end_date", product.getEndDate());
        //jdbcTeamlplte.update return numbers of affected lines
        return jdbcTemplate.update(insertProduct, sqlParameterSource, keyHolder) == 1;
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
            p.setName(rs.getString("description"));
            p.setStartingValue(rs.getLong("starting_value"));
            p.setPathToImg(rs.getString("path_to_image"));
            p.setStartDate(LocalDate.now());
            p.setEndDate(rs.getDate("end_date").toLocalDate());
            p.setVendor_user(new User());
            return p;
        }
    }
}
