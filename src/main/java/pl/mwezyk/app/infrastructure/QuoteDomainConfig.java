package pl.mwezyk.app.infrastructure;

import org.springframework.context.annotation.Bean;
import pl.mwezyk.app.domain.quote.core.QuoteFacade;
import pl.mwezyk.app.domain.quote.core.ports.outgoing.QuoteDatabase;
import pl.mwezyk.app.domain.quote.core.ports.outgoing.QuoteToFileWriter;
import pl.mwezyk.app.domain.quote.infrastructure.QuoteDatabaseAdapter;
import pl.mwezyk.app.domain.quote.infrastructure.QuoteRepository;

public class QuoteDomainConfig {

    @Bean
    public QuoteDatabase quoteDatabase(QuoteRepository quoteRepository) {
        return new QuoteDatabaseAdapter(quoteRepository);
    }

    @Bean
    public QuoteToFileWriter quoteToFileWriter() {
        return new QuoteToFileWriter();
    }

    @Bean
    public QuoteFacade quoteFacade(QuoteDatabase quoteDatabase, QuoteToFileWriter toFileWriter) {
        return new QuoteFacade(quoteDatabase, toFileWriter);
    }

}
