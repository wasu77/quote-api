package pl.mwezyk.quote.domain.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mwezyk.quote.domain.application.model.AddQuoteRequest;
import pl.mwezyk.quote.domain.application.model.ModifyQuoteRequest;
import pl.mwezyk.quote.domain.core.model.*;
import pl.mwezyk.quote.domain.core.ports.incoming.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuoteCommandController {

    private final AddNewQuote addNewQuote;
    private final ModifyQuote modifyQuote;
    private final GetAllQuotes getAllQuotes;
    private final RemoveQuote removeQuote;
    private final GetQuoteById getQuoteById;

    @GetMapping("")
    public ResponseEntity<List<Quote>> getAllQuotes() {
        return ResponseEntity.ok(getAllQuotes.handle());
    }

    @GetMapping("/{quoteId}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long quoteId) {
        GetQuoteCommand command = GetQuoteCommand.builder()
                .id(quoteId)
                .build();
        return ResponseEntity.ok(getQuoteById.handle(command));
    }

    @PostMapping("")
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
    public ResponseEntity<String> modifyQuote(@RequestBody ModifyQuoteRequest request) {
        ModifyQuoteCommand command = ModifyQuoteCommand.builder()
                .id(request.getId())
                .text(request.getText())
                .build();
        modifyQuote.handle(command);
        return ResponseEntity.ok().body("Quote successfully updated");
    }

    @DeleteMapping("/{quoteId}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long quoteId) {
        RemoveQuoteCommand command = RemoveQuoteCommand.builder()
                .id(quoteId)
                .build();
        removeQuote.handle(command);
        return ResponseEntity.noContent().build();
    }


}
