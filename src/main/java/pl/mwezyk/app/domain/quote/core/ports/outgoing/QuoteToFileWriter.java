package pl.mwezyk.app.domain.quote.core.ports.outgoing;

import pl.mwezyk.app.domain.quote.core.model.Quote;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class QuoteToFileWriter {

    public void writeToFile(Quote quote) {
        List<String> lines = Arrays.asList(quote.getText(), quote.getFirstName(), quote.getLastName());

        Path file = Paths.get("quotes-text.txt");
        try {
            Files.write(file, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
