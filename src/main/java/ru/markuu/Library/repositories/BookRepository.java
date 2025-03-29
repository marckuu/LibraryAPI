package ru.markuu.Library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.markuu.Library.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
