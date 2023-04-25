package com.example.demo.dao;

import com.example.demo.model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryJdbcDao implements CategoryDao {

    private final Connection connection = ConnectionManager.getInstance();

    @Override
    public int insert(Category category) {
        String query = "INSERT INTO category (name) VALUES (?);";
        int id = 0;
        try {
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setString(1, category.getName());
            pst.executeUpdate();
            String idQuery = "SELECT MAX(id_post) AS 'last_id' FROM post";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(idQuery);
            if (result.next()) {
                id = result.getInt("last_id");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return id;
    }

    public List<Category> fetchAll() {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM category";
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);
            while (results.next()) {
                int id = results.getInt("id_category");
                String name = results.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return categories;
    }

    public Category findById(int id) {
        throw new RuntimeException();
    }

    public boolean update(Category entity) {
        throw new RuntimeException();
    }

    public boolean delete(int id) {
        throw new RuntimeException();
    }
}
