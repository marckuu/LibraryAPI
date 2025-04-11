package ru.markuu.Library.DTO.responces;

import lombok.Data;
import ru.markuu.Library.models.Tag;

import java.util.ArrayList;
import java.util.List;

@Data
public class AllTagsResponce {
    private List<String> allTags;

    public static AllTagsResponce fromEntity(List<Tag> tags) {
        List<String> allTags = new ArrayList<>();
        for (Tag tag : tags) {
            allTags.add(tag.getName());
        }
        AllTagsResponce allTagsResponce = new AllTagsResponce();
        allTagsResponce.allTags = allTags;
        return allTagsResponce;
    }

}
