package com.company.service;

import com.company.entity.Author;

public interface AuthorService {

    Author getOrThrow(Integer id);
}
