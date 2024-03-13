package com.grp3.bid.controllers.converter;

import com.grp3.bid.entities.Category;
import com.grp3.bid.services.CategoryServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCategory implements Converter<Long, Category> {
    @Autowired
    CategoryServiceInterface categoryService;
    @Override
    public Category convert(Long id) {
        return categoryService.getById(id);
    }
}
