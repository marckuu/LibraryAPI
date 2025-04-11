package ru.markuu.Library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.markuu.Library.DTO.requests.AddBookRequest;
import ru.markuu.Library.DTO.requests.AddQuotesRequest;
import ru.markuu.Library.DTO.requests.AddTagsRequest;
import ru.markuu.Library.DTO.requests.UpdateBookRequest;
import ru.markuu.Library.DTO.responces.AllBooksResponce;
import ru.markuu.Library.DTO.responces.BookResponce;
import ru.markuu.Library.models.Book;
import ru.markuu.Library.models.Quote;
import ru.markuu.Library.models.Tag;
import ru.markuu.Library.repositories.BookRepository;
import ru.markuu.Library.repositories.BookTextRepository;
import ru.markuu.Library.repositories.TagRepository;
import ru.markuu.Library.util.exceptions.BookNotFoundException;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final BaseService   baseService;
    private final TagRepository tagRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BaseService baseService, TagRepository tagRepository) {
        this.bookRepository = bookRepository;
        this.baseService = baseService;
        this.tagRepository = tagRepository;
    }

    public List<AllBooksResponce> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<AllBooksResponce> allBooksResponces = new ArrayList<>();
        for (Book book : books) {
            allBooksResponces.add(AllBooksResponce.fromEntity(book));
        }
        return allBooksResponces;
    }


    public BookResponce getBookById(int id) throws BookNotFoundException {
        return BookResponce.fromEntity(baseService.getBookOrElseThrow(id));
    }

    @Transactional
    public void addBook(AddBookRequest request) {
        bookRepository.save(AddBookRequest.toEntity(request));
    }

    @Transactional
    public void updateBook(int id, UpdateBookRequest request) throws BookNotFoundException {
        Book book = baseService.getBookOrElseThrow(id);
        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setCoverImage(request.getCoverImage());
        book.getBookText().setText(request.getBookText());
        bookRepository.save(book);
    }

    @Transactional
    public void addQuotes(int id, AddQuotesRequest addQuotesRequest) throws BookNotFoundException {
        Book book = baseService.getBookOrElseThrow(id);
        List<Quote> quotes = AddQuotesRequest.toQuotes(addQuotesRequest);
        for (Quote quote : quotes) {
            quote.setSourceBook(book);
        }
        book.setQuotes(quotes);
        bookRepository.save(book);
    }

    @Transactional
    public void addTags(int id, AddTagsRequest addTagsRequest) throws BookNotFoundException {
        Book book = baseService.getBookOrElseThrow(id);

        List<Tag> tagList = new ArrayList<>();
        for (String tag : addTagsRequest.getTags()) {
            if (tagRepository.existsByName(tag)) {
                Tag tagObj = tagRepository.findByName(tag);
                tagList.add(tagObj);
            }
            else {
                Tag tagObj = new Tag();
                tagObj.setName(tag);
                tagList.add(tagObj);
            }
        }

        for (Tag tag : tagList) {
            if (tag.getBooks() == null) {
                tag.setBooks(new ArrayList<>(Collections.singleton(book)));
            } else if (!book.getTags().contains(tag)) {
                tag.getBooks().add(book);
            }
        }
        book.setTags(tagList);
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }


}