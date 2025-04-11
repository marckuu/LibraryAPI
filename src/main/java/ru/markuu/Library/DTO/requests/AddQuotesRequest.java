package ru.markuu.Library.DTO.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import ru.markuu.Library.models.Quote;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddQuotesRequest {

    @NotEmpty(message = "Quotes list should not be null")
    private List<String> quotes;

    public static List<Quote> toQuotes(AddQuotesRequest addQuotesRequest) {
        List<Quote> quoteList = new ArrayList<>();
        for (String quote : addQuotesRequest.getQuotes()) {
            Quote quoteObj = new Quote();
            quoteObj.setText(quote);
            quoteList.add(quoteObj);
        }
        return quoteList;
    }

}
