package com.example.demo.service;

import com.example.demo.dao.CategoryDao;
import com.example.demo.dao.CategoryJdbcDao;
import com.example.demo.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CategoryServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void should_return_all_categories() {
        // GIVEN
        List<Category> expectedCategories = new ArrayList<>();
        expectedCategories.add(new Category(1, "Categorie 1"));
        expectedCategories.add(new Category(2, "Categorie 2"));
        CategoryDao MockedCategoryDao = mock(CategoryDao.class);
        // Mockito : bibliothèque qui permet de créer des objets de simulation.
        Mockito.when(MockedCategoryDao.fetchAll()).thenReturn(expectedCategories);

        // WHEN
        CategoryService categoryService = new CategoryService();
        categoryService.setCategoryDao(MockedCategoryDao);
        List<Category> categoryResult = categoryService.fetchAllCategories();

        // THEN
        Assertions.assertEquals(expectedCategories, categoryResult);
    }
}