package com.example.demo.dao;

import com.example.demo.model.Category;
import com.example.demo.model.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PostJdbcDao implements PostDao {

    private final Connection connectionToPostDb = ConnectionManager.getInstance();


    @Override
    public List<Post> fetchAll() {
        String query = "SELECT p.id_post, p.title, p.author, p.content, p.pictureURL, p.createdAt, c.id_category AS 'category_id', c.name AS 'category_name' " +
                "FROM post p " +
                "INNER JOIN category c ON p.category = c.id_category";
        List<Post> postList = new ArrayList<>();

        try (Statement pst = connectionToPostDb.createStatement()) {
            ResultSet result = pst.executeQuery(query);

            while (result.next()) {
                Post p = new Post(
                    result.getInt("id_post"),
                    result.getString("title"),
                    result.getString("author"),
                    result.getString("content"),
                    result.getString("pictureURL"),
                    result.getTimestamp("createdAt").toLocalDateTime(),
                    new Category( result.getInt("category_id"), result.getString("category_name"))
                );
                postList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }

    public List<Post> fetchByKeywords(String[] keywords) {
        String query = "SELECT p.id_post, p.title, p.author, p.content, p.pictureURL, p.createdAt, c.id_category AS 'category_id', c.name AS 'category_name' " +
            "FROM post p " +
            "INNER JOIN category c ON p.category = c.id_category " +
            "WHERE p.title LIKE CONCAT('%',?,'%');";
        List<Post> postList = new ArrayList<>();

        try {
            PreparedStatement pst = connectionToPostDb.prepareStatement(query);
            pst.setString(1, keywords[0]);
            ResultSet result = pst.executeQuery(query);

            while (result.next()) {
                Post p = new Post(
                        result.getInt("id_post"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getString("content"),
                        result.getString("pictureURL"),
                        result.getTimestamp("createdAt").toLocalDateTime(),
                        new Category( result.getInt("category_id"), result.getString("category_name"))
                );
                postList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }


    @Override
    public int insert(Post post) {
        String title = post.getTitle();
        String author = post.getAuthor();
        String content = post.getContent();
        String pictureUrl = post.getPictureUrl();
        LocalDateTime dateTime = post.getCreatedAt();
        int categoryId = post.getCategory().getId();
        int id = 0;
        try {
            String query = "INSERT INTO post (title, author, content, pictureUrl, createdAt, category) VALUES (?,?,?,?,?,?)";
            PreparedStatement myPreparedStatement = connectionToPostDb.prepareStatement(query);
            myPreparedStatement.setString(1, title);
            myPreparedStatement.setString(2, author);
            myPreparedStatement.setString(3, content);
            myPreparedStatement.setString(4, pictureUrl);
            myPreparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(dateTime));
            myPreparedStatement.setInt(6, categoryId);
            myPreparedStatement.executeUpdate();
            String idQuery = "SELECT MAX(id_post) AS 'last_id' FROM post";
            Statement statement = connectionToPostDb.createStatement();
            ResultSet result = statement.executeQuery(idQuery);
            if (result.next()) {
                id = result.getInt("last_id");
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return id;
    }


    @Override
    public Post findById(int id) {
        String query = "SELECT id_post, title, author, content, pictureURL, createdAt, category AS 'category_id', c.name AS 'category_name'\n" +
                "FROM post\n" +
                "INNER JOIN category c on post.category = c.id_category\n" +
                "WHERE id_post = ?;";
        Post postFound = new Post();

        try {
            PreparedStatement pst = connectionToPostDb.prepareStatement(query);
            pst.setInt(1, id);
            ResultSet result = pst.executeQuery();

            if (result.next()) {
                postFound = new Post(
                        result.getInt("id_post"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getString("content"),
                        result.getString("pictureURL"),
                        result.getTimestamp("createdAt").toLocalDateTime(),
                        new Category( result.getInt("category_id"),result.getString("category_name"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postFound;
    }


    @Override
    public boolean update(Post post) {
        boolean success = false;
        try {
            String query = "UPDATE post " +
                    "SET post.title = ?, post.author = ?, post.content = ?" +
                    "WHERE post.id_post = ?;";
            PreparedStatement pst = connectionToPostDb.prepareStatement(query);
            pst.setString(1, post.getTitle());
            pst.setString(2, post.getAuthor());
            pst.setString(3, post.getContent());
            pst.setInt(4, post.getId());
            pst.executeUpdate();
            success = true;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return success;
    }


    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM post WHERE id_post = ?;";
        boolean success = false;
        try {
            PreparedStatement pst = connectionToPostDb.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            success = true;
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return success;
    }
}
