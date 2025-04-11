package ru.markuu.Library.DTO.responces;

import lombok.Data;
import ru.markuu.Library.models.Book;
import ru.markuu.Library.models.Quote;
import ru.markuu.Library.models.Tag;

import java.util.ArrayList;
import java.util.List;

@Data
public class AllBooksResponce {

    private int id;

    private String title;

    private byte[] coverImage;

    private List<String> tags;

    public static AllBooksResponce fromEntity(Book book) {
        if (book == null) {
            return null;
        }

        AllBooksResponce responce = new AllBooksResponce();
        responce.setId(book.getId());
        responce.setTitle(book.getTitle());
        responce.setCoverImage(book.getCoverImage());

        if (book.getTags() != null) {
            responce.setTags(new ArrayList<>());
            for (Tag tag : book.getTags()) {
                responce.getTags().add(tag.getName());
            }
        }

        return responce;
    }
}
