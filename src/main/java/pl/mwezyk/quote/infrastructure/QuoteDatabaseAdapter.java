package pl.mwezyk.quote.infrastructure;

import pl.mwezyk.quote.domain.core.model.ModifiedQuote;
import pl.mwezyk.quote.domain.core.model.Quote;
import pl.mwezyk.quote.domain.core.model.QuoteIdentifier;
import pl.mwezyk.quote.domain.core.ports.outgoing.QuoteDatabase;

import java.util.List;

public class QuoteDatabaseAdapter implements QuoteDatabase {

    private QuoteRepository quoteRepository;

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Quote getQuoteById(Long quoteId) {
        return quoteRepository.findById(quoteId)
                .orElseThrow(() -> new IllegalArgumentException("Not able to get quote with provided id: " + quoteId));
    }

    @Override
    public QuoteIdentifier save(Quote quote) {
        Quote q = quoteRepository.save(quote);
        return new QuoteIdentifier(q.getId());
    }

    @Override
    public void updateQuote(ModifiedQuote quote) {
        Quote maybeQuote = quoteRepository.findById(quote.getId())
                .orElseThrow(() -> new IllegalArgumentException("Not able to get quote with provided id: " + quote.getId()));

        maybeQuote.setText(quote.getText());
        quoteRepository.save(maybeQuote);

    }

    @Override
    public void removeQuote(Long quoteId) {
        quoteRepository.deleteQuoteById(quoteId);
    }
}
