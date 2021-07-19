package pl.mwezyk.quote.domain.core;

import pl.mwezyk.quote.domain.core.model.*;
import pl.mwezyk.quote.domain.core.ports.incoming.*;
import pl.mwezyk.quote.domain.core.ports.outgoing.QuoteDatabase;

import java.util.List;

public class QuoteFacade implements AddNewQuote, ModifyQuote, RemoveQuote, GetAllQuotes, GetQuoteById {

    private final QuoteDatabase database;

    public QuoteFacade(QuoteDatabase database) {
        this.database = database;
    }

    @Override
    public List<Quote> handle() {
        return database.getAllQuotes();
    }

    @Override
    public Quote handle(GetQuoteCommand getQuoteCommand) {
        return database.getQuoteById(getQuoteCommand.getId());
    }

    @Override
    public QuoteIdentifier handle(AddQuoteCommand addQuoteCommand) {
        Quote quote = new Quote(addQuoteCommand.getText(),
                addQuoteCommand.getFirstName(),
                addQuoteCommand.getLastName());
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
