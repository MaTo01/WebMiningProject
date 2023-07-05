package it.unipd.eis;

import org.apache.commons.lang3.ArrayUtils;

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
            String fieldList = reader.readLine().toLowerCase();
            int titleIndex = ArrayUtils.indexOf(fieldList.split(","), "title");

            FileArticleStorage fas = new FileArticleStorage("Storage");
            String line;
            while((line = reader.readLine()) != null) {
                String title = line.split(",")[titleIndex];
                String bodyText = line.split("\"")[1];
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
