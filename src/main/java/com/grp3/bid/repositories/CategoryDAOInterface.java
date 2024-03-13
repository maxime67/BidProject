package com.grp3.bid.repositories;

import com.grp3.bid.entities.Category;

import java.util.List;

public interface CategoryDAOInterface {
    List<Category> getAll();
    Category getById(Long id);
    List<Category> getByName(String name);
    Integer insertCategory(Category category);

}
