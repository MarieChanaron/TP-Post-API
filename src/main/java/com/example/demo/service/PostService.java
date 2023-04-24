package com.example.demo.service;

import com.example.demo.dao.PostDao;
import com.example.demo.dao.PostJdbcDao;
import com.example.demo.model.Category;
import com.example.demo.model.Post;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

// CRUD
public class PostService {

    private PostDao postDao = new PostJdbcDao();

    private static int idSequence;


    public List<Post> fetchAllPosts() {
        List<Post> posts = postDao.fetchAll();
        idSequence = posts.size();
        return posts;
    }

    public Post fetchPostById(int id) {
        return postDao.findById(id);
    }

    public Post createPost(String title, String author, String content) {
        Post p = new Post(title, author, content, ("https://picsum.photos/200/300?random=" + ++idSequence), LocalDateTime.now());
        int idPost = postDao.insert(p);
        p.setId(idPost);
        return p;
    }

    public boolean deletePost(int id) {
        return postDao.delete(id);
    }

    public boolean updatePost(int id, String title, String author, String content) {
        Post post = new Post(id, title, author, content);
        boolean success = postDao.update(post);
        return success;
    }

}
