package pl.mwezyk.app.domain.quote.application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddQuoteRequest {

    String text;
    String firstName;
    String lastName;

}
