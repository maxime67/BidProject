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
import java.util.List;

@Repository
public class ProductDAO implements ProductDAOInterface{

    private final String getProductByid = "SELECT * FROM PRODUCT WHERE id_PRODUCT = :id_product";
    private final String getProductByIdUser = "SELECT * FROM PRODUCT WHERE id_seller = :id_seller";
    private final String getAll = "SELECT * FROM PRODUCT";
    private final String getByIdCategory = "SELECT * FROM PRODUCT WHERE category_id = :id_category";

    private final String insertProduct = "INSERT INTO PRODUCT (name_product,description,starting_value,path_to_image, start_date,end_date, id_seller, category_id) VALUES (:name_product,:description,:starting_value,:path_to_image,:start_date,:end_date,:id_seller, :category_id)";
    @Autowired
    private final NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    CategoryDAOInterface categoryDAO;
    @Autowired
    UserDAOInterface userDAO;

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
    public int insertProduct(Product product) {
        System.out.println("TEST------------------" + product);
        KeyHolder keyHolder =  new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name_product", product.getName());
        sqlParameterSource.addValue("description", product.getDescription());
        sqlParameterSource.addValue("starting_value", product.getStartingValue());
        sqlParameterSource.addValue("path_to_image", product.getPathToImg());
        sqlParameterSource.addValue("start_date", Timestamp.valueOf(product.getStartDate()));
        sqlParameterSource.addValue("end_date", Timestamp.valueOf(product.getEndDate()));
        sqlParameterSource.addValue("id_seller", product.getSeller().getId());
        sqlParameterSource.addValue("category_id", product.getCategory().getIdCategory());
        //jdbcTeamlplte.update return numbers of affected lines
        return jdbcTemplate.update(insertProduct, sqlParameterSource, keyHolder) == 1 ? keyHolder.getKey().intValue() : -1;
    }

    @Override
    public boolean updateProduct(Integer id, Product product) {
        return false;
    }

    @Override
    public List<Product> getByIdCategory(Integer idCategory) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_category", idCategory);
        return jdbcTemplate.query(getByIdCategory, sqlParameterSource, new ProductRowMapper());
    }

    @Override
    public List<Product> getProductListByIdSeller(User user) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_seller", user.getId());
        return jdbcTemplate.query(getProductByIdUser, sqlParameterSource, new ProductRowMapper());
    }

    public class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product p = new Product();
            p.setId(rs.getInt("id_product"));
            p.setName(rs.getString("name_product"));
            p.setDescription(rs.getString("description"));
            p.setStartingValue(rs.getFloat("starting_value"));
            p.setPathToImg(rs.getString("path_to_image"));
            Timestamp timestampStart = rs.getTimestamp("start_date");
            p.setStartDate(timestampStart.toLocalDateTime());
            Timestamp timestampEnd = rs.getTimestamp("end_date");
            p.setEndDate(timestampEnd.toLocalDateTime());
            p.setSeller(userDAO.getUserById(rs.getInt("id_seller")));
            p.setCategory(categoryDAO.getById(rs.getLong("category_id")));
            return p;
        }
    }
}
