package pl.mwezyk.app.domain.quote.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mwezyk.app.domain.quote.core.model.Quote;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {
    List<Quote> findAll();
}
