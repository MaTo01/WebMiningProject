package it.unipd.eis;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVSource implements Source {
    ArrayList<Article> articles;
    @Override
    public void downloadArticles(String keyword) {
        try (BufferedReader reader = new BufferedReader(new FileReader("dove minchia mettiamo i file csv"))) {
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

    @Override
    public void serializeArticles() {
        if(articles.size() > 0) {
            FileArticleStorage fas = new FileArticleStorage("Storage");

            for (Article a : articles) {
                fas.addArticle(new it.unipd.eis.Article(
                        a.getTitle(),
                        a.getBody()
                ));
            }
        } else {
            throw new IllegalStateException();
        }
    }
}
