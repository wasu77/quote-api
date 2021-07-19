package pl.mwezyk.quote.domain.core.model;

import lombok.Data;
import lombok.Getter;

@Data
public class NewQuote {

    private String text;
    private String firstName;
    private String lastName;
}
