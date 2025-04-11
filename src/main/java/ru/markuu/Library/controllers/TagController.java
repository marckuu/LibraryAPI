package ru.markuu.Library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.markuu.Library.DTO.responces.AllTagsResponce;
import ru.markuu.Library.services.TagServise;

@RestController
@RequestMapping("/tags/")
public class TagController {

    private final TagServise tagServise;

    @Autowired
    public TagController(TagServise tagServise) {
        this.tagServise = tagServise;
    }


    @GetMapping("/")
    public AllTagsResponce getAllTags() {
        return tagServise.getAllTags();
    }
}
