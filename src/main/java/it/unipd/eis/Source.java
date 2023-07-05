package it.unipd.eis;

public interface Source {
    /**
     * Downloads articles from the source.
     *
     * @param query the term(s) used for the article search
     */
    void downloadArticles(String query);

    /**
     * Saves the articles to a storage.
     * @throws IllegalStateException if called before articles are obtained through downloadArticles
     */
    void serializeArticles() throws IllegalStateException;
}
