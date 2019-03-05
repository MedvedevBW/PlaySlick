package com.company.service;

import com.company.util.EntityNotFoundException;
import com.company.entity.Book;
import com.company.repository.GenericRepository;

public class BookServiceImpl implements BookService {
    private final GenericRepository<Book> bookRepository;

    public BookServiceImpl(GenericRepository<Book> bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getOrThrow(Integer id) {
        Book book = bookRepository.getById(id);

        if (book == null) {
            throw new EntityNotFoundException("book not found");
        }

        return book;
    }
}
