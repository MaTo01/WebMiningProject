package it.unipd.eis;

public interface Source {
    void downloadArticles(String keyword);
    void deserializeArticles();
}
