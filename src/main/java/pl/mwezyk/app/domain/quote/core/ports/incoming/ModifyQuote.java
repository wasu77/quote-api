package pl.mwezyk.app.domain.quote.core.ports.incoming;

import pl.mwezyk.app.domain.quote.core.model.ModifyQuoteCommand;

public interface ModifyQuote {
    void handle(ModifyQuoteCommand modifyQuoteCommand);
}
