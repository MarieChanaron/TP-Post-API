package com.example.demo.service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.CategoryJdbcDao;
import com.example.demo.model.Category;
import com.github.javafaker.Cat;

import java.util.List;

public class CategoryService {

    private CategoryDao categoryDao = new CategoryJdbcDao();

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

    public boolean updateCategory(Category category) {
        return categoryDao.update(category);
    }

    public List<Category> fetchAllCategories() {
        return categoryDao.fetchAll();
    }

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public String toString() {
        return "CategoryService{" +
                "categoryDao=" + categoryDao +
                '}';
    }
}
