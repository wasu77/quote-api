package pl.mwezyk.quote.domain.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mwezyk.quote.domain.application.model.AddQuoteRequest;
import pl.mwezyk.quote.domain.application.model.ModifyQuoteRequest;
import pl.mwezyk.quote.domain.application.model.QuoteDto;
import pl.mwezyk.quote.domain.application.model.QuoteMapper;
import pl.mwezyk.quote.domain.core.model.*;
import pl.mwezyk.quote.domain.core.ports.incoming.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
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
    public ResponseEntity<List<QuoteDto>> getAllQuotes() {
        List<QuoteDto> quotes = getAllQuotes.handle()
                .stream()
                .map(QuoteMapper::convertToQuoteDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/{quoteId}")
    public ResponseEntity<QuoteDto> getQuoteById(@PathVariable Long quoteId) {
        GetQuoteCommand command = GetQuoteCommand.builder()
                .id(quoteId)
                .build();

        return ResponseEntity.ok(QuoteMapper.convertToQuoteDTO(getQuoteById.handle(command)));
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
