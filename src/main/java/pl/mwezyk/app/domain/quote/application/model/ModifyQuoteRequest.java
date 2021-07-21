package pl.mwezyk.app.domain.quote.application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModifyQuoteRequest {

    Long id;
    String text;
}
