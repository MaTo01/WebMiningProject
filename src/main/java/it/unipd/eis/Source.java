package it.unipd.eis;

import java.util.ArrayList;

/**
 * Abstract class representing different types of article sources.
 */
public abstract class Source {
    protected ArrayList<Article> articles;
    private static ArticleStorage storage;

    public Source() {
        articles = new ArrayList<>();
    }

    public static void setStorage(ArticleStorage as) {
        storage = as;
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
