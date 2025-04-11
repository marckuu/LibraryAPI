package ru.markuu.Library.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.BinaryJdbcType;


import java.util.List;

@Entity
@Table(name = "book")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title", nullable = false, length = 100, unique = true)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Lob
    @Column(name = "cover_image")
    @JdbcType(BinaryJdbcType.class)
    private byte[] coverImage;

    // Связь с текстом (один к одному)
    @OneToOne(mappedBy = "book")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private BookText bookText;

    // Связь с тэгами (многие ко многим)
    @ManyToMany(mappedBy = "books")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Builder.Default
    private List<Tag> tags = null;

    // Связь с цитатами (один ко многим)
    @OneToMany(mappedBy = "sourceBook")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Builder.Default
    private List<Quote> quotes = null;

}
