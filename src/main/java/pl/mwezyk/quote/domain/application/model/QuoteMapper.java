package pl.mwezyk.quote.domain.application.model;

import org.springframework.util.StringUtils;
import pl.mwezyk.quote.domain.core.model.Quote;

public class QuoteMapper {

    private static final String UNKNOWN = "Unknown";
    private static final String SPACE = " ";

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
