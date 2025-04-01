package ru.markuu.Library.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "quote")
public class Quote {

    public Quote() {

    }

    public Quote(String text, Book sourceBook) {
        this.text = text;
        this.sourceBook = sourceBook;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "quote_text", unique = true)
    private String text;

    // Связь с книгой откуда эта цитата
    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book sourceBook;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Book getSourceBook() {
        return sourceBook;
    }

    public void setSourceBook(Book sourceBook) {
        this.sourceBook = sourceBook;
    }
}
