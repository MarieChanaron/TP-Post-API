package com.example.demo.service;

import com.example.demo.dao.CategoryJdbcDao;
import com.example.demo.model.Category;

import java.util.List;

public class CategoryService {

    CategoryJdbcDao categoryDao = new CategoryJdbcDao();

    public Category insertCategory(Category category) {
        int categoryId = categoryDao.insert(category);
        category.setId(categoryId);
        return category;
    }

    public List<Category> fetchAllCategories() {
        return categoryDao.fetchAll();
    }

    @Override
    public String toString() {
        return "CategoryService{" +
                "categoryDao=" + categoryDao +
                '}';
    }
}
