package com.company.repository;

import com.company.entity.Author;
import java.util.HashMap;

public class FakeAuthorRepository implements GenericRepository<Author> {

    private final HashMap<Integer, Author> authors = new HashMap<Integer, Author>();

    public FakeAuthorRepository() {
        Author author1 = new Author(1, "Vlad", "Medvedev");
        Author author2 = new Author(2, "Alena", "Kolesnikova");

        authors.put(1, author1);
        authors.put(2, author2);
    }

    @Override
    public Author getById(Integer id) {
        return authors.get(id);
    }
}
