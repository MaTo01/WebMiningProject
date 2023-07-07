package it.unipd.eis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVSource extends Source {
    public CSVSource() { }
    @Override
    public void downloadArticles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Sources/CSV/nytimes_articles_v2.csv"))) {
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

    public void downloadArticles(String query) {
        downloadArticles();
    }
}
