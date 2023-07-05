package it.unipd.eis;

public interface Source {
    void downloadArticles(String query);
    void deserializeArticles();
}
