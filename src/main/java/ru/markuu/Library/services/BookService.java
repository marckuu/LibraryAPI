package ru.markuu.Library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.markuu.Library.models.Book;
import ru.markuu.Library.models.Quote;
import ru.markuu.Library.models.Tag;
import ru.markuu.Library.repositories.BookRepository;
import ru.markuu.Library.repositories.BookTextRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BookTextRepository bookTextRepository) {
        this.bookRepository = bookRepository;
    }

    // CRUD

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void addBook(Book book) {
        if (book == null) throw new IllegalArgumentException("Book cannot be null");
        if (book.getTitle() == null || book.getBookText() == null) throw new IllegalArgumentException("Title or Text cannot be null");
//        if (book.getQuotes() == null) book.setQuotes(new ArrayList<>(Collections.singletonList(
//                new Quote("Пустая цитата", book)
//        )));
//        if (book.getTags() == null) book.setTags(new ArrayList<>(Collections.singletonList(
//                new Tag("Пустой тэг", new ArrayList<>(Collections.singletonList(book)))
//        )));
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(int id, Book updatedBook) {
        if (updatedBook == null) throw new IllegalArgumentException("Book cannot be null");
        if (updatedBook.getTitle() == null || updatedBook.getBookText() == null) throw new IllegalArgumentException("Title or Text cannot be null");
//        if (updatedBook.getQuotes().size() > 1 && updatedBook.getQuotes().getFirst().getText().equals("Пустая цитата")) {
//            updatedBook.getQuotes().removeFirst();
//        }
//        if (updatedBook.getTags().size() > 1 && updatedBook.getTags().getFirst().getName().equals("Пустой тэг")) {
//            updatedBook.getTags().removeFirst();
//        }

        if (bookRepository.findById(id).isEmpty()) throw new IllegalArgumentException("Book does not exist");

        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(updatedBook.getTitle());
        bookToUpdate.setDescription(updatedBook.getDescription());
        bookToUpdate.setCoverImage(updatedBook.getCoverImage());
        bookToUpdate.setBookText(updatedBook.getBookText());
        bookToUpdate.setTags(updatedBook.getTags());
        bookToUpdate.setQuotes(updatedBook.getQuotes());
        bookRepository.save(bookToUpdate);
    }

    @Transactional
    public void deleteBook(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Book not found");
        }
    }

}
