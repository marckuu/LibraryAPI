package ru.markuu.Library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.markuu.Library.models.Tag;
import ru.markuu.Library.repositories.TagRepository;

import java.util.List;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    // CRUD

    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public Tag getTagById(int id) {
        return tagRepository.findById(id).orElse(null);
    }

    public void addTag(Tag tag) {
        if (tag.getName() == null) throw new IllegalArgumentException("Tag name is null");
        tagRepository.save(tag);
    }

    public void updateTag(Tag tag) {
        if (tag.getName() == null) throw new IllegalArgumentException("Tag name is null");
        tagRepository.save(tag);
    }

    public void deleteTag(int id) {
        if (tagRepository.existsById(id)) {
            tagRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Tag not found");
        }
    }
}
