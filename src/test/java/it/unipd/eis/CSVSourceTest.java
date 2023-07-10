package it.unipd.eis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVSourceTest {
    private static ArticleStorage articleStorage = new FileArticleStorage("./Storage/Test");
    private static final String sourceName = "nytimes_articles_v2.csv";
    private CSVSource source;

    @BeforeEach
    public void setup() {
        CSVSource.setStorage(articleStorage);
        source = new CSVSource(sourceName);
        CSVSource.clearStorage();
    }

    @Test
    public void testDownloadArticles() {
        source.downloadArticles();

        assertEquals(1000, articleStorage.getArticleCount());
    }
}