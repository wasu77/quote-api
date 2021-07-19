package pl.mwezyk.quote.domain.core.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetQuoteCommand {
    Long id;
}
