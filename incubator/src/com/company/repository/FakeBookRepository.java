package com.company.repository;

import com.company.entity.Book;
import java.util.HashMap;

public class FakeBookRepository implements GenericRepository<Book> {

    private final HashMap<Integer, Book> books = new HashMap<Integer, Book>();
    public FakeBookRepository() {
        books.put(1, new Book(1, "Kolobok"));
        books.put(2, new Book(2, "Repka"));
    }

    public Book getById(Integer id) {
        return books.get(id);
    }
}
