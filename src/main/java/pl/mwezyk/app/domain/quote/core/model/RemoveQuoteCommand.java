package pl.mwezyk.app.domain.quote.core.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RemoveQuoteCommand {
    Long id;
}
