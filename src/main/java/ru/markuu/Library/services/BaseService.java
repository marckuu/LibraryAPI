package ru.markuu.Library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.markuu.Library.models.Book;
import ru.markuu.Library.models.Tag;
import ru.markuu.Library.repositories.BookRepository;
import ru.markuu.Library.repositories.TagRepository;
import ru.markuu.Library.util.exceptions.BookNotFoundException;
import ru.markuu.Library.util.exceptions.TagNotFoundException;

@Service
public class BaseService {

    private final BookRepository bookRepository;
    private final TagRepository tagRepository;

    @Autowired
    public BaseService(BookRepository bookRepository, TagRepository tagRepository) {
        this.bookRepository = bookRepository;
        this.tagRepository = tagRepository;
    }


    public Book getBookOrElseThrow(int id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow();
    }


}
