package pl.mwezyk.app.domain.quote.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import pl.mwezyk.app.domain.quote.core.model.ModifiedQuote;
import pl.mwezyk.app.domain.quote.core.model.Quote;
import pl.mwezyk.app.domain.quote.core.model.QuoteIdentifier;
import pl.mwezyk.app.domain.quote.core.ports.outgoing.QuoteDatabase;

import java.util.List;

@RequiredArgsConstructor
public class QuoteDatabaseAdapter implements QuoteDatabase {

    private final QuoteRepository quoteRepository;

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
        try {
            quoteRepository.deleteById(quoteId);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
