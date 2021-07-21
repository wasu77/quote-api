package pl.mwezyk.app.domain.quote.core;

import org.junit.jupiter.api.*;
import pl.mwezyk.app.domain.quote.application.model.QuoteDto;
import pl.mwezyk.app.domain.quote.core.model.*;
import pl.mwezyk.app.domain.quote.domain.core.model.*;
import pl.mwezyk.app.quote.domain.quote.core.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class QuoteFacadeTest {

    private QuoteFacade quoteFacade;
    private InMemoryQuoteDatabase quoteDatabase;

    @BeforeEach
    void init() {
        quoteDatabase = new InMemoryQuoteDatabase();
        quoteFacade = new QuoteFacade(quoteDatabase);
    }

    private static final String TEST_QUOTE_TEXT = "TEST_QUOTE_TEXT";
    private static final String TEST_MODIFIED_QUOTE_TEXT = "TEST_QUOTE_TEXT_MODIFIED";
    private static final String TEST_QUOTE_FIRST_NAME = "TEST_FIRST_NAME";
    private static final String TEST_QUOTE_LAST_NAME = "TEST_LAST_NAME";

    @Test
    @DisplayName("Add new quote")
    void givenAddQuoteComand_whenNewQuoteAdded_thenReturnSameQuote() {
        //given
        AddQuoteCommand addCommand = provideAddQuoteCommand();

        //when
        QuoteIdentifier identifier = quoteFacade.handle(addCommand);

        //then
        Quote quote = quoteDatabase.getQuoteById(identifier.getId());

        Assertions.assertNotNull(quote);
        Assertions.assertEquals(TEST_QUOTE_TEXT, quote.getText());
        Assertions.assertEquals(TEST_QUOTE_FIRST_NAME, quote.getFirstName());
        Assertions.assertEquals(TEST_QUOTE_LAST_NAME, quote.getLastName());

        Assertions.assertEquals(1, quoteFacade.handle().size());
    }

    @Test
    @DisplayName("Add new quote, remove it and assert it does not exist in database")
    void givenRemoveQuoteCommand_whenQuoteRemoved_thenAssertQuoteDoesNotExistInDatabase() {
        //given
        AddQuoteCommand addCommand = provideAddQuoteCommand();

        QuoteIdentifier identifier = quoteFacade.handle(addCommand);
        Quote quote = quoteDatabase.getQuoteById(identifier.getId());
        Assertions.assertNotNull(quote);

        //when
        RemoveQuoteCommand removeCommand = RemoveQuoteCommand.builder()
                .id(identifier.getId())
                .build();

        quoteFacade.handle(removeCommand);

        //then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> quoteDatabase.getQuoteById(identifier.getId()));
    }

    @Test
    @DisplayName("Add new quote, modify it and assert content is modified")
    void givenModifyQuoteCommand_whenQuoteModified_thenAssertQuoteContentIsModifiedInDatabase() {
        //given
        AddQuoteCommand addCommand = provideAddQuoteCommand();

        QuoteIdentifier identifier = quoteFacade.handle(addCommand);
        Quote quote = quoteDatabase.getQuoteById(identifier.getId());
        Assertions.assertNotNull(quote);

        //when
        ModifyQuoteCommand modifyCommand = ModifyQuoteCommand.builder()
                .id(identifier.getId())
                .text(TEST_MODIFIED_QUOTE_TEXT)
                .build();

        quoteFacade.handle(modifyCommand);

        //then
        quote = quoteDatabase.getQuoteById(identifier.getId());
        Assertions.assertNotNull(quote);
        Assertions.assertEquals(TEST_MODIFIED_QUOTE_TEXT, quote.getText());
    }

    @Test
    @DisplayName("Add quotes to database and assert all are returned")
    void givenMultipleQuotes_whenAllAddedToDatabase_thenAssertAllAreReturned() {
        //given
        List<AddQuoteCommand> commands = provideMultipleAddQuoteCommands();
        commands.forEach(quoteFacade::handle);

        //when
        List<QuoteDto> quotes = quoteFacade.handle();

        //then
        Assertions.assertEquals(commands.size(), quotes.size());
        Assertions.assertEquals(commands.size(), quoteDatabase.getAllQuotes().size());
    }

    @Test
    @DisplayName("Get quotes list when database is empty")
    void assertListIsEmptyWhenNoQuotesInDatabaseTest() {
        //when
        List<QuoteDto> quotes = quoteFacade.handle();
        //then
        Assertions.assertEquals(0, quotes.size());
    }

    @Test
    @DisplayName("Get not existing quote")
    void assertExceptionIsThrownWhenQuoteWithGivenIdDoesNotExistInDatabase() {
        //given
        GetQuoteCommand command = GetQuoteCommand.builder()
                .id(999L)
                .build();

        //when//then
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> quoteFacade.handle(command));
    }

    private List<AddQuoteCommand> provideMultipleAddQuoteCommands() {
        List<AddQuoteCommand> commands = new ArrayList<>();
        IntStream.range(0, 10).parallel().forEach(i -> commands.add(provideAddQuoteCommand()));
        return commands;
    }

    private AddQuoteCommand provideAddQuoteCommand() {
        return AddQuoteCommand
                .builder()
                .text(TEST_QUOTE_TEXT)
                .firstName(TEST_QUOTE_FIRST_NAME)
                .lastName(TEST_QUOTE_LAST_NAME)
                .build();
    }
}
