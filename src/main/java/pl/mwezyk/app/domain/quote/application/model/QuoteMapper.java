package pl.mwezyk.app.domain.quote.application.model;

import org.springframework.util.StringUtils;
import pl.mwezyk.app.domain.quote.core.model.Quote;

public class QuoteMapper {

    protected static final String UNKNOWN = "Unknown";
    protected static final String SPACE = " ";

    public static QuoteDto convertToQuoteDTO(Quote quote) {
        return QuoteDto.builder()
                .id(quote.getId())
                .text(quote.getText())
                .author(provideAuthor(quote))
                .build();
    }

    private static String provideAuthor(Quote quote) {
        if (!StringUtils.hasText(quote.getFirstName()) && !StringUtils.hasText(quote.getLastName())) {
            return UNKNOWN;
        } else {
            String author = quote.getFirstName() + SPACE + quote.getLastName();
            return StringUtils.trimWhitespace(author);
        }
    }
}
