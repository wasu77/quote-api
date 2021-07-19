package pl.mwezyk.quote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import pl.mwezyk.quote.infrastructure.QuoteDomainConfig;

@SpringBootApplication
@Import({QuoteDomainConfig.class})
public class QuoteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuoteApiApplication.class, args);
	}

}
