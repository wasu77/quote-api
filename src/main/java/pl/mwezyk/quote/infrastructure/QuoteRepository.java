package pl.mwezyk.quote.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mwezyk.quote.domain.core.model.Quote;

import java.util.List;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, Long> {

    List<Quote> findAll();
    void deleteQuoteById(Long id);

}
