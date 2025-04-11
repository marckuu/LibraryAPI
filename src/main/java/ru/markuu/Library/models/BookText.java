package ru.markuu.Library.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_text")
@Setter()
@Getter()
@NoArgsConstructor()
@AllArgsConstructor
@Builder
public class BookText {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Lob
    @Column(name = "content", nullable = false)
    private String text;

    // Owning side один к одному с книгой
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;

}
