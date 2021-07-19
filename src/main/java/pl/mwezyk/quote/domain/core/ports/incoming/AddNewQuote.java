package pl.mwezyk.quote.domain.core.ports.incoming;

import pl.mwezyk.quote.domain.core.model.AddQuoteCommand;
import pl.mwezyk.quote.domain.core.model.QuoteIdentifier;

public interface AddNewQuote {
    QuoteIdentifier handle(AddQuoteCommand addQuoteCommand);
}
