package com.grp3.bid.repositories;

import com.grp3.bid.entities.Category;
import com.grp3.bid.entities.Product;
import com.grp3.bid.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public class CategoryDAO implements CategoryDAOInterface{
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    private final String getAll = "SELECT * FROM CATEGORY";
    private final String getById = "SELECT * FROM CATEGORY WHERE id_category = :id_category";
    private final String getByName = "SELECT * FROM CATEGORY WHERE name_category LIKE %:name_category%";
    private final String insertCategory = "INSERT INTO CATEGORY (name_category) VALUES (:name_category)";
    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query(getAll, new CategoryRowMapper());
    }

    @Override
    public Category getById(Long id) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("id_category", id);
        return jdbcTemplate.queryForObject(getById, sqlParameterSource, new CategoryRowMapper());

    }

    @Override
    public List<Category> getByName(String name) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name_category", name);
        return jdbcTemplate.query(getByName, sqlParameterSource, new CategoryRowMapper());

    }

    @Override
    public Integer insertCategory(Category category) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("name_category", category.getNameCategory());
        return jdbcTemplate.update(insertCategory, sqlParameterSource, keyHolder) == 1 ? keyHolder.getKey().intValue() : -1;
    }

    public class CategoryRowMapper implements RowMapper<Category> {
        @Override
        public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
            Category c = new Category();
            c.setIdCategory(rs.getLong("id_category"));
            c.setNameCategory(rs.getString("name_category"));
            return c;
        }
    }
}
