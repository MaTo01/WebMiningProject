package it.unipd.eis;

import java.util.ArrayList;

public abstract class Source {
    protected ArrayList<Article> articles = new ArrayList<>();
    private static ArticleStorage storage;

    public Source() {
        storage = new FileArticleStorage("Storage");
    }

    abstract public void downloadArticles();
    abstract public void downloadArticles(String query);

    protected void serializeArticles() {
        for (Article a : articles) {
            storage.addArticle(a);
        }
    }

    public static void clearStorage() {
        storage.clearStorage();
    }
}
