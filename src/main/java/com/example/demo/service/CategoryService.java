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

    public boolean deleteCategory(int idCategory) {
        boolean done = false;
        if (categoryDao.setDefaultCategory(idCategory)) { // First change all the categories of these posts to default category
            done = categoryDao.delete(idCategory);
        }
        return done;
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
