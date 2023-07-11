package it.unipd.eis;

import com.apiguardian.GuardianContentAPI;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.http.client.HttpResponseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Source used to download and serialize articles from TheGuardian API.
 */
public class TheGuardianAPISource extends Source {
    private String apiKey;
    private String apiKeyFilePath = "Sources/TheGuardianAPIKey.txt";
    private static final int pageSize = 200;
    private int numArticles = 1000;

    /**
     * Constructs a TheGuardianAPISource using the default API key file path.
     */
    public TheGuardianAPISource() {
        getApiKey();
    }

    /**
     * Constructs a TheGuardianAPISource using the specified API key file path.
     * @param keyPath the file path of the API key
     */
    public TheGuardianAPISource(String keyPath) {
        apiKeyFilePath = keyPath;
        getApiKey();
    }

    /**
     * Sets the number of articles to be downloaded.
     * @param numArticles number of articles to download
     */
    public void setNumArticles(int numArticles) {
        this.numArticles = numArticles;
    }

    /**
     * The implementation of downloadArticles() without a search query is disabled for this Source.
     */
    public void downloadArticles() {
        throw new UnsupportedOperationException();
    }

    /**
     * Queries TheGuardian API using the specified search string to obtain articles.
     * @param query the String to use as keyword for the search
     */
    @Override
    public void downloadArticles(String query) {
        if(storage == null) {
            throw new IllegalStateException("Uninitialized or invalid storage.");
        }

        GuardianContentAPI guardianApi = new GuardianContentAPI(apiKey);
        guardianApi.setPageSize(pageSize);

        try {
            int pages = (int) Math.ceil((double) numArticles / pageSize);
            for (int i = 1; i <= pages; i++) {
                com.apiguardian.bean.Response response = guardianApi.getContent(query);

                if(response.getStatus().equals("ok")) {
                    int left = numArticles - ((i - 1) * pageSize);
                    com.apiguardian.bean.Article[] responseArticles = response.getResults();

                    for (int j = 0; j < responseArticles.length && j < left; j++) {
                        articles.add(new Article(
                                responseArticles[j].getWebTitle(),
                                responseArticles[j].getBodyText()
                        ));
                    }
                } else {
                    throw new HttpResponseException(400, "Download request failed.");
                }
            }

            serializeArticles();
        } catch(UnirestException | HttpResponseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the API key from the previously specified file.
     */
    private void getApiKey() {
        try (BufferedReader reader = new BufferedReader(new FileReader(apiKeyFilePath))) {
            apiKey = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
