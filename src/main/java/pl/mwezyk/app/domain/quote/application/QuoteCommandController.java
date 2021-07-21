package pl.mwezyk.app.domain.quote.application;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mwezyk.app.domain.quote.application.model.AddQuoteRequest;
import pl.mwezyk.app.domain.quote.application.model.ModifyQuoteRequest;
import pl.mwezyk.app.domain.quote.application.model.QuoteDto;
import pl.mwezyk.app.domain.quote.core.model.*;
import pl.mwezyk.app.domain.quote.core.ports.incoming.*;
import pl.mwezyk.app.domain.quote.domain.core.model.*;
import pl.mwezyk.app.domain.quote.domain.core.ports.incoming.*;
import pl.mwezyk.app.quote.domain.quote.core.model.*;
import pl.mwezyk.app.quote.domain.quote.core.ports.incoming.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
@Api
public class QuoteCommandController {

    @Qualifier("AddNewQuote")
    private final AddNewQuote addNewQuote;
    @Qualifier("ModifyQuote")
    private final ModifyQuote modifyQuote;
    @Qualifier("GetAllQuotes")
    private final GetAllQuotes getAllQuotes;
    @Qualifier("RemoveQuote")
    private final RemoveQuote removeQuote;
    @Qualifier("GetQuoteById")
    private final GetQuoteById getQuoteById;

    @GetMapping("")
    @ApiOperation(value = "Get list of all quotes")
    public ResponseEntity<List<QuoteDto>> getAllQuotes() {
        return ResponseEntity.ok(getAllQuotes.handle());
    }

    @GetMapping("/{quoteId}")
    @ApiOperation(value = "Get quote with given id")
    public ResponseEntity<QuoteDto> getQuoteById(@PathVariable Long quoteId) {
        GetQuoteCommand command = GetQuoteCommand.builder()
                .id(quoteId)
                .build();
        try {
            return ResponseEntity.ok(getQuoteById.handle(command));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @ApiOperation(value = "Add new quote")
    public ResponseEntity<QuoteIdentifier> addNewQuote(@RequestBody AddQuoteRequest request) {
        AddQuoteCommand command = AddQuoteCommand.builder()
                .text(request.getText())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();
        QuoteIdentifier identifier = addNewQuote.handle(command);
        return new ResponseEntity<>(identifier, HttpStatus.CREATED);
    }

    @PatchMapping("")
    @ApiOperation(value = "Modify existing quote")
    public ResponseEntity<String> modifyQuote(@RequestBody ModifyQuoteRequest request) {
        ModifyQuoteCommand command = ModifyQuoteCommand.builder()
                .id(request.getId())
                .text(request.getText())
                .build();
        try {
            modifyQuote.handle(command);
            return ResponseEntity.ok().body("Quote successfully updated");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{quoteId}")
    @ApiOperation(value = "Remove existing quote")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long quoteId) {
        RemoveQuoteCommand command = RemoveQuoteCommand.builder()
                .id(quoteId)
                .build();
        try {
            removeQuote.handle(command);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }

    }


}
