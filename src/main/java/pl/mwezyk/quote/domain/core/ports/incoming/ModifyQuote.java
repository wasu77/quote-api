package pl.mwezyk.quote.domain.core.ports.incoming;

import pl.mwezyk.quote.domain.core.model.ModifyQuoteCommand;

public interface ModifyQuote {
    void handle(ModifyQuoteCommand modifyQuoteCommand);
}
