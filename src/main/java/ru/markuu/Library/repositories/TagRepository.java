package ru.markuu.Library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.markuu.Library.models.Tag;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    public Tag findByName(String name);

    public boolean existsByName(String name);
}
