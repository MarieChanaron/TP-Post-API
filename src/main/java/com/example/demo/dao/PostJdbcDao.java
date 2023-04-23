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
                        result.getLong("id_post"),
                        result.getString("title"),
                        result.getString("author"),
                        result.getString("content"),
                        result.getString("pictureURL"),
                        result.getTimestamp("createdAt").toLocalDateTime(),
                        new Category( result.getLong("category_id"),result.getString("category_name"))
                );
                postList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postList;
    }


    @Override
    public boolean insert(Post post) {
        String title = post.getTitle();
        String author = post.getAuthor();
        String content = post.getContent();
        String pictureUrl = post.getPictureUrl();
        LocalDateTime dateTime = post.getCreatedAt();
        try {
            String query = "INSERT INTO post (title, author, content, pictureUrl, createdAt) VALUES (?,?,?,?,?)";
            PreparedStatement myPreparedStatement = connectionToPostDb.prepareStatement(query);
            myPreparedStatement.setString(1, title);
            myPreparedStatement.setString(2, author);
            myPreparedStatement.setString(3, content);
            myPreparedStatement.setString(4, pictureUrl);
            myPreparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(dateTime));
            myPreparedStatement.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return true;
    }


    @Override
    public Post findById(Long aLong) {
        return null;
    }

    @Override
    public void update(Post entity) {

    }

    @Override
    public void delete(Post entity) {

    }
}
