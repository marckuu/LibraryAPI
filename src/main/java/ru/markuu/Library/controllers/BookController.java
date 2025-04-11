package ru.markuu.Library.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.markuu.Library.DTO.requests.AddBookRequest;
import ru.markuu.Library.DTO.requests.AddQuotesRequest;
import ru.markuu.Library.DTO.requests.AddTagsRequest;
import ru.markuu.Library.DTO.requests.UpdateBookRequest;
import ru.markuu.Library.DTO.responces.AllBooksResponce;
import ru.markuu.Library.DTO.responces.BookResponce;
import ru.markuu.Library.services.BookService;
import ru.markuu.Library.util.exceptions.BookNotFoundException;
import ru.markuu.Library.util.exceptions.BookillegalArgumentException;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<AllBooksResponce> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponce getBookById(@PathVariable int id) throws BookNotFoundException {
        return bookService.getBookById(id);
    }

    @PostMapping("/")
    public ResponseEntity<?> addBook(@RequestBody @Valid AddBookRequest bookRequest,
                                     BindingResult bindingResult) {
        validOrThrowException(bindingResult);
        bookService.addBook(bookRequest);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/quotes/{id}")
    public ResponseEntity<?> addQuotes(@PathVariable int id, @RequestBody @Valid AddQuotesRequest quotesRequest,
                                       BindingResult bindingResult) throws BookNotFoundException {
        validOrThrowException(bindingResult);
        bookService.addQuotes(id, quotesRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/quotes/{id}")
    public List<String> getAllQuotesByBookId(@PathVariable int id) throws BookNotFoundException {
        BookResponce bookResponce = bookService.getBookById(id);
        return bookResponce.getQuotes();
    }

    @PatchMapping("/tags/{id}")
    public ResponseEntity<?> addTags(@PathVariable int id, @RequestBody @Valid AddTagsRequest tagsRequest,
                                     BindingResult bindingResult) throws BookNotFoundException {
        validOrThrowException(bindingResult);
        bookService.addTags(id, tagsRequest);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @RequestBody @Valid UpdateBookRequest updateBookRequest,
                                        BindingResult bindingResult) throws BookNotFoundException {
        validOrThrowException(bindingResult);
        bookService.updateBook(id, updateBookRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    private void validOrThrowException(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("\n");
            }
            throw new BookillegalArgumentException(errorMessage.toString());
        }

    }
}