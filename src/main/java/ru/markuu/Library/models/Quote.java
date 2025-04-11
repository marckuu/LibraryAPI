package ru.markuu.Library.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "quote")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "quote_text", unique = true, nullable = false)
    private String text;

    // Связь с книгой откуда эта цитата
    @ManyToOne()
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book sourceBook;

}
