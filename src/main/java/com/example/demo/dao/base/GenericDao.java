package com.example.demo.dao.base;

import java.util.List;

public interface GenericDao<T, ID> {

    int insert(T entity);

    List<T> fetchAll();

    T findById(int id);

    boolean update(T entity);

    boolean delete(int id);

}
