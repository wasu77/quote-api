package pl.mwezyk.app.domain.quote.core.ports.incoming;

import pl.mwezyk.app.domain.quote.core.model.RemoveQuoteCommand;

public interface RemoveQuote {
    void handle(RemoveQuoteCommand removeQuoteCommand);
}
