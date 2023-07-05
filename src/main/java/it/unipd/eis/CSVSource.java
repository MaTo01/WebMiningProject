package it.unipd.eis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVSource implements Source {

    @Override
    public void downloadArticles(String keyword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deserializeArticles() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dove minchia mettiamo i file csv"))) {
            FileArticleStorage fas = new FileArticleStorage("Storage");

            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(reader);

            for (CSVRecord record : records) {
                String title = record.get("Title");
                String bodyText = record.get("Body");

                fas.addArticle(new Article(
                        title,
                        bodyText
                ));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
