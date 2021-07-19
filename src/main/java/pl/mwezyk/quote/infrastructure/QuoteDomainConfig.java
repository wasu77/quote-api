package pl.mwezyk.quote.infrastructure;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import pl.mwezyk.quote.domain.core.QuoteFacade;
import pl.mwezyk.quote.domain.core.ports.incoming.*;
import pl.mwezyk.quote.domain.core.ports.outgoing.QuoteDatabase;

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
