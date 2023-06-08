package it.unipd.eis;

import org.junit.jupiter.api.*;
import java.util.*;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

public class FileArticleStorageTest {
    private static final String FILE_PATH = "./Storage";
    private ArticleStorage articleStorage;

    @BeforeEach
    public void setup() {
        articleStorage = new FileArticleStorage(FILE_PATH);
        articleStorage.clearStorage();
    }

    @AfterAll
    static void cleanup() {
        /*
        try {
            Path filePath = Paths.get(FILE_PATH);
            Files.walk(filePath)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    @Test
    public void testAddArticle() {
        Article article = new Article("1", "Title 1", "Body text 1");
        articleStorage.addArticle(article);

        String filePath = FILE_PATH + "/" + article.getId() + ".txt";
        assertTrue(Files.exists(Paths.get(filePath)));

        assertEquals(1, articleStorage.getArticleCount());
        assertTrue(articleStorage.containsArticle(article));
    }

    @Test
    public void testRemoveArticle() {
        Article article = new Article("1", "Title 1", "Body text 1");
        articleStorage.addArticle(article);
        assertEquals(1, articleStorage.getArticleCount());

        articleStorage.removeArticle(article);
        assertEquals(0, articleStorage.getArticleCount());
        assertFalse(articleStorage.containsArticle(article));
    }

    @Test
    public void testGetAllArticles() {
        Article article1 = new Article("1", "Title 1", "Body text 1");
        Article article2 = new Article("2", "Title 2", "Body text 2");
        articleStorage.addArticle(article1);
        articleStorage.addArticle(article2);

        List<Article> articles = articleStorage.getAllArticles();
        assertEquals(2, articles.size());
        assertTrue(articles.contains(article1));
        assertTrue(articles.contains(article2));
    }

    @Test
    public void testGetArticleCount() {
        assertEquals(0, articleStorage.getArticleCount());

        Article article1 = new Article("1", "Title 1", "Body text 1");
        articleStorage.addArticle(article1);
        assertEquals(1, articleStorage.getArticleCount());

        Article article2 = new Article("2", "Title 2", "Body text 2");
        articleStorage.addArticle(article2);
        assertEquals(2, articleStorage.getArticleCount());
    }

    @Test
    public void testContainsArticle() {
        Article article = new Article("1", "Title 1", "Body text 1");
        assertFalse(articleStorage.containsArticle(article));

        articleStorage.addArticle(article);
        assertTrue(articleStorage.containsArticle(article));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(articleStorage.isEmpty());

        Article article = new Article("1", "Title 1", "Body text 1");
        articleStorage.addArticle(article);
        assertFalse(articleStorage.isEmpty());
    }

    @Test
    public void testClearStorage() {
        Article article = new Article("1", "Title 1", "Body text 1");
        articleStorage.addArticle(article);
        assertFalse(articleStorage.isEmpty());

        articleStorage.clearStorage();
        assertTrue(articleStorage.isEmpty());
    }
}
