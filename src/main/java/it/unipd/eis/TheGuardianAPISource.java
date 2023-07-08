package it.unipd.eis;

import com.apiguardian.GuardianContentApi;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.HttpResponseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TheGuardianAPISource extends Source {
    private static String apiKey;
    private static final String apiKeyFilePath = "Storage/TheGuardianAPIKey.txt";
    private static final int pageSize = 200;
    private static final int numArticles = 1000;

    public TheGuardianAPISource() {
        getApiKey();
    }

    public void downloadArticles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void downloadArticles(String query) {
        GuardianContentApi guardianApi = new GuardianContentApi(apiKey);
        guardianApi.setPageSize(pageSize);

        try {
            for (int i = 1; i <= numArticles/pageSize; i++) {
                guardianApi.setPage(i);
                com.apiguardian.bean.Response response = guardianApi.getContent(query);
                if(response.getStatus().equals("ok")) {

                    int k = response.getCurrentPage();
                    com.apiguardian.bean.Article[] responseArticles = response.getResults();
                    for (com.apiguardian.bean.Article a : responseArticles) {
                        articles.add(new Article(
                                a.getWebTitle(),
                                a.getBodyText()
                        ));
                    }
                } else {
                    throw new HttpResponseException(400, "Download request failed");
                }
            }

            serializeArticles();
        } catch(UnirestException | HttpResponseException e) {
            e.printStackTrace();
        }
    }

    private void getApiKey() {
        try (BufferedReader reader = new BufferedReader(new FileReader(apiKeyFilePath))) {
            apiKey = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
