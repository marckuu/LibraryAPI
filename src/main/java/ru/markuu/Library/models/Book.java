package ru.markuu.Library.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    public Book() {

    }

    public Book(String title, String description, byte[] coverImage, BookText bookText, List<Tag> tags, List<Quote> quotes) {
        this.title = title;
        this.description = description;
        this.coverImage = coverImage;
        this.bookText = bookText;
        this.tags = tags;
        this.quotes = quotes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "cover_image")
    private byte[] coverImage;

    // Связь с текстом (один к одному)
    @OneToOne(mappedBy = "book")
    private BookText bookText;

    // Связь с тэгами (многие ко многим)
    @ManyToMany(mappedBy = "books")
    private List<Tag> tags;

    // Связь с цитатами (один ко многим)
    @OneToMany(mappedBy = "sourceBook")
    private List<Quote> quotes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(byte[] coverImage) {
        this.coverImage = coverImage;
    }

    public BookText getBookText() {
        return bookText;
    }

    public void setBookText(BookText bookText) {
        this.bookText = bookText;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}
