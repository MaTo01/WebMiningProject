package it.unipd.eis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheGuardianAPISourceTest {
    private static ArticleStorage articleStorage = new FileArticleStorage("./Storage/Test");
    private final int NUM_ARTICLES_TO_DOWNLOAD = 10;
    private final String API_KEY_PATH = "./Sources/TheGuardianAPIKey.txt";
    private TheGuardianAPISource source;

    @BeforeEach
    public void setup() {
        TheGuardianAPISource.setArticleStorage(articleStorage);
        source = new TheGuardianAPISource(API_KEY_PATH);
        source.setNumArticles(NUM_ARTICLES_TO_DOWNLOAD);
        TheGuardianAPISource.clearStorage();
    }

    @Test
    public void testDownloadArticles() {
        source.downloadArticles("nuclear power");

        assertEquals(NUM_ARTICLES_TO_DOWNLOAD, articleStorage.getArticleCount());
    }
}