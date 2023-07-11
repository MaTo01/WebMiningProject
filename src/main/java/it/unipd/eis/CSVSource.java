package it.unipd.eis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Source used to read and serialize articles from CSV files.
 */
public class CSVSource extends Source {
    private final String FILE_NAME;

    /**
     * Constructs a CSVSource for the specified file in the Sources/CSV directory
     * @param file the name of the CSV file
     */
    public CSVSource(String file) {
        FILE_NAME = "Sources/CSV/" + file;
    }

    /**
     * Reads all articles from the CSV file and serializes them.
     */
    @Override
    public void downloadArticles() {
        if(articleStorage == null) {
            throw new IllegalStateException("Uninitialized or invalid storage.");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : records) {
                String title = record.get("Title");
                String body = record.get("Body");

                articles.add(new Article(
                        title,
                        body
                ));
            }

            serializeArticles();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Dummy method; a search query is not necessary for CSV files.
     * @param query dummy parameter
     */
    public void downloadArticles(String query) {
        downloadArticles();
    }
}
