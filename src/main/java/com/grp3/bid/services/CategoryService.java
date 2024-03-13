package com.grp3.bid.services;

import com.grp3.bid.entities.Category;
import com.grp3.bid.repositories.CategoryDAOInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements CategoryServiceInterface{
    @Autowired
    CategoryDAOInterface categoryDAO;
    @Override
    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryDAO.getById(id);
    }

    @Override
    public List<Category> getByName(String name) {
        return categoryDAO.getByName(name);
    }

    @Override
    public Integer insertCategory(Category category) {
        return categoryDAO.insertCategory(category);
    }
}
