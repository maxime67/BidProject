package com.grp3.bid.services;

import com.grp3.bid.entities.Category;

import java.util.List;

public interface CategoryServiceInterface {
    List<Category> getAll();
    Category getById(Long id);
    List<Category> getByName(String name);
    Integer insertCategory(Category category);

}
