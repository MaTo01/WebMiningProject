package it.unipd.eis;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface for storing and managing articles.
 */
public interface ArticleStorage {

    /**
     * Adds an article to the storage.
     *
     * @param article the article to add
     */
    void addArticle(Article article);

    /**
     * Adds a list of articles to the storage.
     *
     * @param articles the articles to add
     */
    void addArticles(List<Article> articles);
    /**
     * Removes an article from the storage.
     *
     * @param article the article to remove
     */
    void removeArticle(Article article);

    /**
     * Retrieves all articles from the storage.
     *
     * @return a list of all articles
     */
    ArrayList<Article> getAllArticles();

    /**
     * Retrieves the number of articles in the storage.
     *
     * @return the number of articles
     */
    int getArticleCount();

    /**
     * Checks if the storage contains the specified article.
     *
     * @param article the article to check
     * @return true if the article is contained in the storage, false otherwise
     */
    boolean containsArticle(Article article);

    /**
     * Checks if the storage is empty.
     *
     * @return true if the storage is empty, false otherwise
     */
    boolean isEmpty();

    /**
     * Clears the storage, removing all articles.
     */
    void clearStorage();
}


