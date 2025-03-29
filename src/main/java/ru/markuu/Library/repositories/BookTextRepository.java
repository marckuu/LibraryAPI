package ru.markuu.Library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.markuu.Library.models.BookText;

@Repository
public interface BookTextRepository extends JpaRepository<BookText, Integer> {

}
