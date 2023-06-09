package com.example.demo.dao;

import com.example.demo.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJdbcDao implements UserDao {

    @Override
    public List<User> fetchAll() {
        throw new RuntimeException();
    }

    @Override
    public int insert(User entity) {
        throw new RuntimeException();
    }

    @Override
    public User findById(int id) {
        throw new RuntimeException();
    }

    @Override
    public boolean update(User entity) {
        throw new RuntimeException();
    }

    @Override
    public boolean delete(int id) {
        throw new RuntimeException();
    }

    @Override
    public User findByUsername(String username) {
        Connection connection = ConnectionManager.getInstance();
        String query = "SELECT id_user, username, password FROM user WHERE username=?";
        User userFound = null;

        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, username);
            ResultSet result = pst.executeQuery();
            if (result.next()) {
                userFound = new User(
                        result.getInt("id_user"),
                        result.getString("username"),
                        result.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFound;
    }
}
