package com.grp3.bid.controllers.converter;

import com.grp3.bid.entities.Category;
import com.grp3.bid.services.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategory implements Converter<String, Category> {

    CategoryServiceInterface categoryService;

    public StringToCategory(CategoryServiceInterface categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Category convert(String id) {
        Long theId = Long.parseLong(id);
        return categoryService.getById(theId);
    }
}
