package com.company;

import com.company.annotations.Bean;
import com.company.controller.UniversalController;
import com.company.entity.Author;
import com.company.entity.Book;
import com.company.repository.FakeAuthorRepository;
import com.company.repository.FakeBookRepository;
import com.company.repository.GenericRepository;
import com.company.service.AuthorService;
import com.company.service.AuthorServiceImpl;
import com.company.service.BookService;
import com.company.service.BookServiceImpl;
import com.company.util.MiddleNameGenerator;
import com.company.util.RandomStringGenerator;

public class Components {
    private final GenericRepository<Author> authorRepository = new FakeAuthorRepository();
    private final RandomStringGenerator randomStringGenerator = new MiddleNameGenerator();
    private final AuthorService authorService = new AuthorServiceImpl(authorRepository, randomStringGenerator);

    private final GenericRepository<Book> bookRepository = new FakeBookRepository();
    private final BookService bookService = new BookServiceImpl(bookRepository);

    private final UniversalController authorController = new UniversalController(authorService, bookService);

    @Bean
    public UniversalController getAuthorController() {
        return authorController;
    }
}
