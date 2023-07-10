package it.unipd.eis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheGuardianAPISourceTest {
    private static ArticleStorage articleStorage = new FileArticleStorage("./Storage/Test");
    private final int numArticlesTest = 10;
    private final String apiKeyPath = "./Storage/TheGuardianAPIKey.txt";
    private TheGuardianAPISource source;

    @BeforeEach
    public void setup() {
        TheGuardianAPISource.setStorage(articleStorage);
        source = new TheGuardianAPISource(apiKeyPath);
        source.setNumArticles(numArticlesTest);
    }

    @Test
    public void testDownloadArticles() {
        TheGuardianAPISource.clearStorage();
        source.downloadArticles("nuclear power");

        assertEquals(numArticlesTest, articleStorage.getArticleCount());
    }
}