package pl.mwezyk.quote.domain.core.ports.incoming;

import pl.mwezyk.quote.domain.application.model.QuoteDto;
import pl.mwezyk.quote.domain.core.model.GetQuoteCommand;

public interface GetQuoteById {
    QuoteDto handle(GetQuoteCommand getQuoteCommand);
}
