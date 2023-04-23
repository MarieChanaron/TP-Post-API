package com.example.demo.dao.base;

import java.util.List;

public interface GenericDao<T, ID> {

    boolean insert(T entity);

    List<T> fetchAll();

    T findById(int id);

    void update(T entity);

    void delete(T entity);

}
