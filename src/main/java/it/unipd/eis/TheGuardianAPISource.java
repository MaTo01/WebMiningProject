package it.unipd.eis;

import com.apiguardian.GuardianContentApi;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.HttpResponseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TheGuardianAPISource extends Source {
    private String apiKey;

    public TheGuardianAPISource() {
        getApiKey();
    }

    @Override
    public void downloadArticles(String query) {
        GuardianContentApi guardianApi = new GuardianContentApi(apiKey);
        try {
            com.apiguardian.bean.Response response = guardianApi.getContent(query);

            if(response.getStatus().equals("ok")) {
                com.apiguardian.bean.Article[] responseArticles = response.getResults();

                for (com.apiguardian.bean.Article a : responseArticles) {
                    articles.add(new Article(
                            a.getWebTitle(),
                            a.getBodyText()
                    ));
                }

                serializeArticles();
            } else {
                throw new RuntimeException("AAAAAAAAAAAAAAAAAAAAA");
            }
        } catch(UnirestException e) {
            e.printStackTrace();
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
