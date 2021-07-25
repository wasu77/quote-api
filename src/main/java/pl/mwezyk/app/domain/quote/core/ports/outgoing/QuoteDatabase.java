package pl.mwezyk.app.domain.quote.core.ports.outgoing;

import pl.mwezyk.app.domain.quote.core.model.ModifiedQuote;
import pl.mwezyk.app.domain.quote.core.model.Quote;
import pl.mwezyk.app.domain.quote.core.model.QuoteIdentifier;

import java.util.List;

public interface QuoteDatabase {
    List<Quote> getAllQuotes();
    Quote getQuoteById(Long quoteId);
    QuoteIdentifier save(Quote quote);
    void updateQuote(ModifiedQuote quote);
    void removeQuote(Long quoteId);
}
