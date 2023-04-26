package com.example.demo.service;

import com.example.demo.dao.PostDao;
import com.example.demo.dao.PostJdbcDao;
import com.example.demo.model.Category;
import com.example.demo.model.Post;

import java.time.LocalDateTime;
import java.util.List;


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

    public Post createPost(String title, String author, String content, int catId) {
        Category c = new Category(catId);
        Post p = new Post(title, author, content, ("https://picsum.photos/200/300?random=" + ++idSequence), LocalDateTime.now(), c);
        int idPost = postDao.insert(p);
        p.setId(idPost);
        return p;
    }

    // In case the category is not provided: set the category to default category (id 1 = default).
    public Post createPost(String title, String author, String content) {
        Category c = new Category(1); // default
        Post p = new Post(title, author, content, ("https://picsum.photos/200/300?random=" + ++idSequence), LocalDateTime.now(), c);
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

    public List<Post> searchByKeywords(String keywords) {
        String[] keywordsArray = keywords.split(" ");
        List<Post> posts = postDao.fetchByKeywords(keywordsArray);
        return posts;
    }

}
