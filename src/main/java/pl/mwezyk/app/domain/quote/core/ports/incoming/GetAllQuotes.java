package pl.mwezyk.app.domain.quote.core.ports.incoming;

import pl.mwezyk.app.domain.quote.application.model.QuoteDto;

import java.util.List;

public interface GetAllQuotes {
    List<QuoteDto> handle();
}
