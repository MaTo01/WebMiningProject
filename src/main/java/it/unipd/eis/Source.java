package it.unipd.eis;

import java.util.ArrayList;

/**
 * Abstract class representing different types of sources.
 */
public abstract class Source {
    protected ArrayList<Article> articles;
    private final static ArticleStorage storage = new FileArticleStorage("Storage");

    public Source() {
        articles = new ArrayList<>();
    }

    /**
     * Downloads articles from a source that doesn't require a search query (e.g. CSV file).
     */
    abstract public void downloadArticles();
    /**
     * Downloads articles from a source that requires a search query (e.g. TheGuardian API).
     */
    abstract public void downloadArticles(String query);

    /**
     * Serializes the downloaded articles.
     */
    protected void serializeArticles() {
        storage.addArticles(articles);
    }

    /**
     * Clears all the stored articles.
     */
    public static void clearStorage() {
        storage.clearStorage();
    }
}
