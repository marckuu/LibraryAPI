package ru.markuu.Library.DTO.responces;

import lombok.Data;
import ru.markuu.Library.models.Quote;

import java.util.ArrayList;
import java.util.List;

@Data
public class AllQuotes {

    private List<String> allQuotes;

    public static List<String> fromEntity(List<Quote> quotes) {
        List<String> allQuotes = new ArrayList<>();
        for (Quote quote : quotes) {
            allQuotes.add(quote.getText());
        }
        return allQuotes;
    }

}
