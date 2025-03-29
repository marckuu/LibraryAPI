package ru.markuu.Library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.markuu.Library.models.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

}
