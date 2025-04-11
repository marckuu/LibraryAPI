package ru.markuu.Library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.markuu.Library.DTO.responces.AllTagsResponce;
import ru.markuu.Library.repositories.TagRepository;

import java.util.List;

@Service
public class TagServise {

    private final TagRepository tagRepository;

    @Autowired
    public TagServise(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public AllTagsResponce getAllTags() {
        return AllTagsResponce.fromEntity(tagRepository.findAll());
    }
}
