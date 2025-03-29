package ru.markuu.Library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.markuu.Library.models.Quote;
import ru.markuu.Library.repositories.QuoteRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    // CRUD

    public List<Quote> getALlQuotes() {
        return quoteRepository.findAll();
    }

    public Quote getQuoteById(int id) {
        return quoteRepository.findById(id).orElse(null);
    }

    public void addQuote(Quote quote) {
        if (quote.getText() == null) throw new IllegalArgumentException("Quote text is null");
        if (quote.getSourceBook() == null) throw new IllegalArgumentException("Quote sourceBook is null");

        quoteRepository.save(quote);
    }

    public void updateQuote(int id, Quote updatedQuote) {
        if (updatedQuote.getText() == null) throw new IllegalArgumentException("Quote text is null");
        if (updatedQuote.getSourceBook() == null) throw new IllegalArgumentException("Quote sourceBook is null");

        if (quoteRepository.findById(id).isEmpty()) throw new IllegalArgumentException("Book does not exist");

        quoteRepository.save(updatedQuote);
    }

    public void deleteQuote(int id) {
        if (quoteRepository.existsById(id)) {
            quoteRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException("Quote not found");
        }
    }


}
