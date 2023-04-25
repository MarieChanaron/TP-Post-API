package com.example.demo.dao;

import com.example.demo.dao.base.GenericDao;
import com.example.demo.model.Post;

import java.util.List;

public interface PostDao extends GenericDao<Post, Long> {
    List<Post> fetchByKeywords(String[] keywordsArray);
}
