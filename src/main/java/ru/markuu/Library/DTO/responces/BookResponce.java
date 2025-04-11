package ru.markuu.Library.DTO.responces;

import lombok.Data;
import ru.markuu.Library.models.Book;
import ru.markuu.Library.models.Quote;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class BookResponce {

    private String title;

    private String description;

    private byte[] coverImage;

    private String bookText;

    private List<String> quotes;

    public static BookResponce fromEntity(Book book) {
        if (book == null) {
            return null;
        }

        BookResponce responce = new BookResponce();
        responce.setTitle(book.getTitle());
        responce.setDescription(book.getDescription());
        responce.setCoverImage(book.getCoverImage());
        responce.setBookText(book.getBookText().getText());

        if (book.getQuotes() != null) {
            responce.setQuotes(new ArrayList<>());
            for (Quote quote : book.getQuotes()) {
                responce.getQuotes().add(quote.getText());
            }
        }

        return responce;
    }

}
