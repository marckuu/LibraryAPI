package ru.markuu.Library.DTO.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.markuu.Library.models.Tag;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddTagsRequest {

    @NotEmpty(message = "Tags list should not be null")
    List<String> tags;

    public List<Tag> toTags() {
        List<Tag> tagList = new ArrayList<>();
        for (String tag : tags) {
            Tag tagObj = new Tag();
            tagObj.setName(tag);
            tagList.add(tagObj);
        }
        return tagList;
    }

}
