package pl.mwezyk.quote.domain.core;

import pl.mwezyk.quote.domain.core.model.ModifiedQuote;
import pl.mwezyk.quote.domain.core.model.Quote;
import pl.mwezyk.quote.domain.core.model.QuoteIdentifier;
import pl.mwezyk.quote.domain.core.ports.outgoing.QuoteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryQuoteDatabase implements QuoteDatabase {

    ConcurrentHashMap<Long, Quote> allQuotes = new ConcurrentHashMap<>();

    @Override
    public List<Quote> getAllQuotes() {
        return new ArrayList<>(allQuotes.values());
    }

    @Override
    public Quote getQuoteById(Long quoteId) {
        return allQuotes.get(quoteId);
    }

    @Override
    public QuoteIdentifier save(Quote quote) {
        Long quoteId = new Random().nextLong();
        allQuotes.put(quoteId, quote);
        return new QuoteIdentifier(quoteId);
    }

    @Override
    public void updateQuote(ModifiedQuote quote) {
        Quote quoteToModify = allQuotes.get(quote.getId());
        quoteToModify.setText(quote.getText());
        allQuotes.remove(quote.getId());
        allQuotes.put(quote.getId(), quoteToModify);
    }

    @Override
    public void removeQuote(Long quoteId) {
        allQuotes.remove(quoteId);
    }
}
