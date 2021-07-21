package pl.mwezyk.app.domain.quote.application.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class QuoteDto {

    Long id;
    String text;
    String author;
}
