package it.unipd.eis;

import com.apiguardian.*;
import com.apiguardian.bean.Article;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TheGuardianAPISource implements Source {

    private com.apiguardian.bean.Article[] responseArticles;
    private String apiKey;

    public TheGuardianAPISource() {
        getApiKey();
    }
    @Override
    public void downloadArticles(String query) {
        GuardianContentApi guardianApi = new GuardianContentApi(apiKey);
        try {
            responseArticles = guardianApi.getContent(query).getResults();
        } catch(UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deserializeArticles() {
        FileArticleStorage fas = new FileArticleStorage("Storage");
        for (com.apiguardian.bean.Article a : responseArticles) {
            fas.addArticle(new it.unipd.eis.Article(
                    a.getWebTitle(),
                    a.getBodyText()
            ));
        }
    }

    private void getApiKey() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Storage/guardianApiKey.txt"))) {
            apiKey = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
