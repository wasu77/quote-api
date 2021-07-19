package pl.mwezyk.quote.domain.core.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddQuoteCommand {
    String text;
    String firstName;
    String lastName;
}
