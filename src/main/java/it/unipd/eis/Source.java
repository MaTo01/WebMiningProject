package it.unipd.eis;

import java.util.ArrayList;

/**
 * Abstract class representing different types of article sources.
 */
public abstract class Source {
    protected ArrayList<Article> articles;
    protected static ArticleStorage articleStorage;

    /**
     * Default constructor, initializes the articles list.
     */
    public Source() {
        articles = new ArrayList<>();
    }

    /**
     * Sets the ArticleStorage to be used by all Sources.
     * @param as
     */
    public static void setArticleStorage(ArticleStorage as) {
        articleStorage = as;
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
        articleStorage.addArticles(articles);
    }

    /**
     * Clears all the stored articles.
     */
    public static void clearStorage() {
        if(articleStorage != null) {
            articleStorage.clearStorage();
        }
    }
}
