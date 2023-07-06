package it.unipd.eis;

import java.util.ArrayList;

public abstract class Source {
    ArrayList<Article> articles;
    ArticleStorage storage;

    public Source() {
        articles = new ArrayList<Article>();
        storage = new FileArticleStorage("Storage");
    }

    abstract public void downloadArticles(String query);

    protected void serializeArticles(){
        for (Article a : articles) {
            storage.addArticle(a);
        }
    }
}
