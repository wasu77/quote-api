package pl.mwezyk.quote.domain.core.ports.incoming;

import pl.mwezyk.quote.domain.core.model.Quote;

import java.util.List;

public interface GetAllQuotes {
    List<Quote> handle();
}
