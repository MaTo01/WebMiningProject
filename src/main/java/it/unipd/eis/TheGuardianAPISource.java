package it.unipd.eis;

import com.apiguardian.GuardianContentApi;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TheGuardianAPISource implements Source {

    private ArrayList<Article> articles;
    private String apiKey;

    public TheGuardianAPISource() {
        getApiKey();
    }
    @Override
    public void downloadArticles(String query) {
        GuardianContentApi guardianApi = new GuardianContentApi(apiKey);
        try {
            com.apiguardian.bean.Response response = guardianApi.getContent(query);

            if(response.getStatus() == "ok") {
                com.apiguardian.bean.Article[] responseArticles = response.getResults();

                for (com.apiguardian.bean.Article a : responseArticles) {
                    articles.add(new Article(
                            a.getWebTitle(),
                            a.getBodyText()
                    ));
                }

                serializeArticles();
            } else {
                //throw some kind of exception
            }
        } catch(UnirestException e) {
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

    private void getApiKey() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Storage/guardianApiKey.txt"))) {
            apiKey = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
