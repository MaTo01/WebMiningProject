package it.unipd.eis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TermExtractorTest {
    private final int MAX_TERMS_TO_SAVE = 50;
    private static ArticleStorage articleStorage = new FileArticleStorage("./Storage/Test");
    private static TermStorage termStorage = new FileTermStorage("./Storage/Test");
    private TermExtractor extractor;

    @BeforeEach
    public void setup() {
        extractor = new TermExtractor(MAX_TERMS_TO_SAVE);
        extractor.setArticleStorage(articleStorage);
        extractor.setTermStorage(termStorage);
        termStorage.clearStorage();
        articleStorage.clearStorage();
    }

    @Test
    public void testExtractTerms() {
        Article article1 = new Article("Test 1", "The quick brown fox jumps over the lazy dog");
        Article article2 = new Article("Test 2", "Now is the time for all good men to come to the aid of the party");
        articleStorage.addArticle(article1);
        articleStorage.addArticle(article2);
        extractor.extractTerms();

        assertEquals(14, termStorage.getTermCount());
        assertTrue(termStorage.containsTerm(new Term("test", 2)));
    }
}
