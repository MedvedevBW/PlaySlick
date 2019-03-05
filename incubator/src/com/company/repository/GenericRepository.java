package com.company.repository;

public interface GenericRepository<T> {

    T getById(Integer id);
}
