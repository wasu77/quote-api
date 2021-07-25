package pl.mwezyk.app.domain.quote.application.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.mwezyk.app.domain.quote.core.model.Quote;

public class QuoteMapperTest {

    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";

    @Test
    @DisplayName("Print 'unknown' when first name and last name are empty")
    void givenEmptyFirstNameAndLastName_whenMappingEntityToDto_thenReturnUnknownWord() {
        //given
        Quote quote = new Quote("", "", "");

        //when
        QuoteDto quoteDto = QuoteMapper.convertToQuoteDTO(quote);

        //then
        Assertions.assertEquals(QuoteMapper.UNKNOWN, quoteDto.getAuthor());
    }

    @Test
    @DisplayName("Assert no whitespaces when first name is empty")
    void givenEmptyFirstName_whenMappingEntityToDto_thenAssertNoTrailingWhitespaces() {
        //given
        Quote quote = new Quote("", QuoteMapper.SPACE, LAST_NAME);

        //when
        QuoteDto quoteDto = QuoteMapper.convertToQuoteDTO(quote);

        //then
        Assertions.assertEquals(LAST_NAME, quoteDto.getAuthor());
    }

    @Test
    @DisplayName("Assert first name and last name is combined with space")
    void givenFirstNameAndLastName_whenMappingEntityToDto_thenAssertNamesCombinedWithSpace() {
        //given
        Quote quote = new Quote("", FIRST_NAME, LAST_NAME);

        //when
        QuoteDto quoteDto = QuoteMapper.convertToQuoteDTO(quote);

        //then
        Assertions.assertEquals(FIRST_NAME + QuoteMapper.SPACE + LAST_NAME, quoteDto.getAuthor());
    }
}
