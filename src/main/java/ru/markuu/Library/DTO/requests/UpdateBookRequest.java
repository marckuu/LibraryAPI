package ru.markuu.Library.DTO.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ru.markuu.Library.models.Book;
import ru.markuu.Library.models.BookText;

@Data
public class UpdateBookRequest {

    @NotBlank(message = "Book title should not be null or empty")
    private String title;

    @NotBlank(message = "Book description should not be null or empty")
    private String description;

    @NotNull(message = "Cover image should not be null")
    private byte[] coverImage;

    @NotNull(message = "Book text should not be null")
    private String bookText;

    public   static Book toEntity(UpdateBookRequest updateBookRequest) {
        Book book = new Book();
        book.setTitle(updateBookRequest.getTitle());
        book.setDescription(updateBookRequest.getDescription());
        book.setCoverImage(updateBookRequest.getCoverImage());

        BookText bookText = new BookText();
        bookText.setText(updateBookRequest.getBookText());
        bookText.setBook(book);

        book.setBookText(bookText);

        return book;
    }
}
