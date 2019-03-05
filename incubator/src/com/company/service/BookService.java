package com.company.service;

import com.company.entity.Book;

public interface BookService {

    public Book getOrThrow(Integer id);
}
