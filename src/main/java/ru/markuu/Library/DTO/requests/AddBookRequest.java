package ru.markuu.Library.DTO.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.markuu.Library.models.Book;
import ru.markuu.Library.models.BookText;

import java.util.List;

@Data
public class AddBookRequest {

    @NotBlank(message = "Book title should not be null or empty")
    private String title;

    @NotBlank(message = "Book description should not be null or empty")
    private String description;

    @NotNull(message = "Cover image should not be null")
    private byte[] coverImage;

    @NotNull(message = "Book text should not be null")
    private String bookText;

    public static Book toEntity(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setDescription(addBookRequest.getDescription());
        book.setCoverImage(addBookRequest.getCoverImage());

        BookText bookText = new BookText();
        bookText.setText(addBookRequest.getBookText());
        bookText.setBook(book);

        book.setBookText(bookText);

        return book;
    }

}
