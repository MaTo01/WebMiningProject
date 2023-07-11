package it.unipd.eis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CSVSourceTest {
    private static ArticleStorage articleStorage = new FileArticleStorage("./Storage/Test");
    private static final String FILE_NAME = "nytimes_articles_v2.csv";
    private CSVSource source;

    @BeforeEach
    public void setup() {
        CSVSource.setArticleStorage(articleStorage);
        source = new CSVSource(FILE_NAME);
        CSVSource.clearStorage();
    }

    @Test
    public void testDownloadArticles() {
        source.downloadArticles();

        assertEquals(1000, articleStorage.getArticleCount());
    }
}