package pl.mwezyk.quote.domain.core.ports.outgoing;

import pl.mwezyk.quote.domain.core.model.ModifiedQuote;
import pl.mwezyk.quote.domain.core.model.Quote;
import pl.mwezyk.quote.domain.core.model.QuoteIdentifier;

import java.util.List;

public interface QuoteDatabase {
    List<Quote> getAllQuotes();
    Quote getQuoteById(Long quoteId);
    QuoteIdentifier save(Quote quote);
    void updateQuote(ModifiedQuote quote);
    void removeQuote(Long quoteId);
}
