package pl.mwezyk.app.domain.quote.core.ports.incoming;

import pl.mwezyk.app.domain.quote.application.model.QuoteDto;
import pl.mwezyk.app.domain.quote.core.model.GetQuoteCommand;

public interface GetQuoteById {
    QuoteDto handle(GetQuoteCommand getQuoteCommand);
}
