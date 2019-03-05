package com.company.service;

import com.company.util.EntityNotFoundException;
import com.company.entity.Author;
import com.company.repository.GenericRepository;
import com.company.util.RandomStringGenerator;

public class AuthorServiceImpl implements AuthorService {

    private final GenericRepository<Author> authorRepository;
    private final RandomStringGenerator generator;

    public AuthorServiceImpl(
            GenericRepository<Author> authorRepository,
            RandomStringGenerator middleNameGenerator
    ) {
        this.authorRepository = authorRepository;
        this.generator = middleNameGenerator;
    }

    @Override
    public Author getOrThrow(Integer id) {
        Author author = authorRepository.getById(id);

        if (author == null) {
            throw new EntityNotFoundException("author not found");
        }

        author.setMiddleName(generator.generate());

        return author;
    }
}
