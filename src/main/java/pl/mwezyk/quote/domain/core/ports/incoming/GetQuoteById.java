package pl.mwezyk.quote.domain.core.ports.incoming;

import pl.mwezyk.quote.domain.core.model.GetQuoteCommand;
import pl.mwezyk.quote.domain.core.model.Quote;

public interface GetQuoteById {
    Quote handle(GetQuoteCommand getQuoteCommand);
}
