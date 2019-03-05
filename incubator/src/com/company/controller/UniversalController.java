package com.company.controller;

import com.company.annotations.Controller;
import com.company.annotations.Path;
import com.company.annotations.PathVariable;
import com.company.service.AuthorService;
import com.company.service.BookService;
import com.company.util.EntityNotFoundException;

@Controller
public class UniversalController {

    private AuthorService authorService;
    private BookService bookService;

    public UniversalController(
            AuthorService authorService,
            BookService bookService
    ) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Path("/author/{id}")
    public String getAuthor(
            @PathVariable("id") Integer id
    ) {
        String result;
        try {
            result = authorService.getOrThrow(id).toString();
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
            result = "404 NOT FOUND";
        }

        return result;
    }

    @Path("/book/{id}")
    public String getBook(
            @PathVariable("id") Integer id
    ) {
        String result;
        try {
            result = bookService.getOrThrow(id).toString();
        } catch (EntityNotFoundException ex) {
            System.out.println(ex.getMessage());
            result = "404 NOT FOUND";
        }

        return result;
    }
}
