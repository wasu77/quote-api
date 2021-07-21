package pl.mwezyk.app.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import pl.mwezyk.app.domain.quote.core.QuoteFacade;
import pl.mwezyk.app.domain.quote.core.ports.incoming.*;
import pl.mwezyk.app.domain.quote.core.ports.outgoing.QuoteDatabase;
import pl.mwezyk.app.domain.quote.infrastructure.QuoteDatabaseAdapter;
import pl.mwezyk.app.domain.quote.infrastructure.QuoteRepository;

public class QuoteDomainConfig {

    @Bean
    public QuoteDatabase quoteDatabase(QuoteRepository quoteRepository) {
        return new QuoteDatabaseAdapter(quoteRepository);
    }

    @Bean
    @Qualifier("AddNewQuote")
    public AddNewQuote addNewQuote(QuoteDatabase quoteDatabase) {
        return new QuoteFacade(quoteDatabase);
    }

    @Bean
    @Qualifier("GetAllQuotes")
    public GetAllQuotes getAllQuotes(QuoteDatabase quoteDatabase) {
        return new QuoteFacade(quoteDatabase);
    }

    @Bean
    @Qualifier("GetQuoteById")
    public GetQuoteById getQuoteById(QuoteDatabase quoteDatabase) {
        return new QuoteFacade(quoteDatabase);
    }

    @Bean
    @Qualifier("ModifyQuote")
    public ModifyQuote modifyQuote(QuoteDatabase quoteDatabase) {
        return new QuoteFacade(quoteDatabase);
    }

    @Bean
    @Qualifier("RemoveQuote")
    public RemoveQuote removeQuote(QuoteDatabase quoteDatabase) {
        return new QuoteFacade(quoteDatabase);
    }
}
