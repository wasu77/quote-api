package pl.mwezyk.app.domain.quote.core;

import pl.mwezyk.app.domain.quote.application.model.QuoteDto;
import pl.mwezyk.app.domain.quote.application.model.QuoteMapper;
import pl.mwezyk.app.domain.quote.core.model.*;
import pl.mwezyk.app.domain.quote.core.ports.incoming.*;
import pl.mwezyk.app.domain.quote.core.ports.outgoing.QuoteDatabase;
import pl.mwezyk.app.domain.quote.core.ports.outgoing.QuoteToFileWriter;

import java.util.List;
import java.util.stream.Collectors;

public class QuoteFacade implements AddNewQuote, ModifyQuote, RemoveQuote, GetAllQuotes, GetQuoteById {

    private final QuoteDatabase database;
    private final QuoteToFileWriter toFileWriter;

    public QuoteFacade(QuoteDatabase database, QuoteToFileWriter toFileWriter) {
        this.database = database;
        this.toFileWriter = toFileWriter;
    }

    @Override
    public List<QuoteDto> handle() {
        return database.getAllQuotes()
                .stream()
                .map(QuoteMapper::convertToQuoteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuoteDto handle(GetQuoteCommand getQuoteCommand) {
        return QuoteMapper.convertToQuoteDTO(database.getQuoteById(getQuoteCommand.getId()));
    }

    @Override
    public QuoteIdentifier handle(AddQuoteCommand addQuoteCommand) {
        Quote quote = new Quote(addQuoteCommand.getText(),
                addQuoteCommand.getFirstName(),
                addQuoteCommand.getLastName());
        toFileWriter.writeToFile(quote);
        return database.save(quote);
    }

    @Override
    public void handle(ModifyQuoteCommand modifyQuoteCommand) {
        ModifiedQuote quote = new ModifiedQuote(modifyQuoteCommand.getId(),
                modifyQuoteCommand.getText());
        database.updateQuote(quote);

    }

    @Override
    public void handle(RemoveQuoteCommand removeQuoteCommand) {
        database.removeQuote(removeQuoteCommand.getId());
    }
}
