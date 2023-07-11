package it.unipd.eis;

import java.util.*;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileArticleStorageTest {
    private static final String DIR_PATH = "./Storage/Test";
    private ArticleStorage articleStorage;

    @BeforeEach
    public void setup() {
        articleStorage = new FileArticleStorage(DIR_PATH);
        articleStorage.clearStorage();
    }

    @Test
    public void testAddArticle() {
        Article article1 = new Article("Title 1", "Body text 1");
        Article article2 = new Article("Title 2", "Body text 2");
        articleStorage.addArticle(article1);
        articleStorage.addArticle(article2);

        assertEquals(2, articleStorage.getArticleCount());
        assertTrue(articleStorage.containsArticle(article1));
        assertTrue(articleStorage.containsArticle(article2));
    }

    @Test
    public void testAddArticles() {
        Article article1 = new Article("Title 1", "Body text 1");
        Article article2 = new Article("Title 2", "Body text 2");
        ArrayList<Article> articles = new ArrayList<>();
        articles.add(article1);
        articles.add(article2);
        articleStorage.addArticles(articles);

        List<Article> articlesTest = articleStorage.getAllArticles();
        assertEquals(2, articlesTest.size());
        assertTrue(articlesTest.contains(article1));
        assertTrue(articlesTest.contains(article2));
    }

    @Test
    public void testRemoveArticle() {
        Article article = new Article("Title 1", "Body text 1");
        articleStorage.addArticle(article);
        assertEquals(1, articleStorage.getArticleCount());

        articleStorage.removeArticle(article);
        assertEquals(0, articleStorage.getArticleCount());
        assertFalse(articleStorage.containsArticle(article));
    }

    @Test
    public void testGetAllArticles() {
        Article article1 = new Article("Title 1", "Body text 1");
        Article article2 = new Article("Title 2", "Body text 2");
        articleStorage.addArticle(article1);
        articleStorage.addArticle(article2);

        ArrayList<Article> articles = articleStorage.getAllArticles();
        assertEquals(2, articles.size());
        assertTrue(articles.contains(article1));
        assertTrue(articles.contains(article2));
    }

    @Test
    public void testGetArticleCount() {
        assertEquals(0, articleStorage.getArticleCount());

        Article article1 = new Article("Title 1", "Body text 1");
        articleStorage.addArticle(article1);
        assertEquals(1, articleStorage.getArticleCount());

        Article article2 = new Article("Title 2", "Body text 2");
        articleStorage.addArticle(article2);
        assertEquals(2, articleStorage.getArticleCount());
    }

    @Test
    public void testContainsArticle() {
        Article article = new Article("Title 1", "Body text 1");
        assertFalse(articleStorage.containsArticle(article));

        articleStorage.addArticle(article);
        assertTrue(articleStorage.containsArticle(article));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(articleStorage.isEmpty());

        Article article1 = new Article("Title 1", "Body text 1");
        Article article2 = new Article("Title 2", "Body text 2");
        articleStorage.addArticle(article1);
        articleStorage.addArticle(article2);
        assertFalse(articleStorage.isEmpty());
    }

    @Test
    public void testClearStorage() {
        Article article1 = new Article("Title 1", "Body text 1");
        articleStorage.addArticle(article1);
        assertFalse(articleStorage.isEmpty());

        articleStorage.clearStorage();
        assertTrue(articleStorage.isEmpty());
    }
}
