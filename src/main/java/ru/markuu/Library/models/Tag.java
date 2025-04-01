package ru.markuu.Library.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tag")
public class Tag {

    public Tag() {

    }

    public Tag(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "tag_name", unique = true)
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "tag_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
