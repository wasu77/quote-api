package pl.mwezyk.quote.domain.core.ports.incoming;

import pl.mwezyk.quote.domain.core.model.RemoveQuoteCommand;

public interface RemoveQuote {
    void handle(RemoveQuoteCommand removeQuoteCommand);
}
