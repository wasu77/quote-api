package pl.mwezyk.app.domain.quote.core.ports.incoming;

import pl.mwezyk.app.domain.quote.core.model.AddQuoteCommand;
import pl.mwezyk.app.domain.quote.core.model.QuoteIdentifier;

public interface AddNewQuote {
    QuoteIdentifier handle(AddQuoteCommand addQuoteCommand);
}
