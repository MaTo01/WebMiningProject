package it.unipd.eis;

import java.util.List;

public interface ArticleStorage {
    void addArticle(Article article);
    void removeArticle(Article article);
    List<Article> getAllArticles();
    int getArticleCount();
    boolean containsArticle(Article article);
    boolean isEmpty();
    void clearStorage();
}


