package it.unipd.eis;

import java.io.*;
import java.util.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * A class that implements the {@link ArticleStorage} interface and provides storage for articles
 * in a file-based storage system.
 */
public class FileArticleStorage implements ArticleStorage {
    private final String filePath;

    /**
     * Constructs a new instance of {@code FileArticleStorage} with the specified file path.
     *
     * @param dirPath the path of the directory used for storing the articles file
     */
    public FileArticleStorage(String dirPath) {
        StorageUtils.createDirectoryIfNotExists(dirPath);
        this.filePath = dirPath + "/articles.json";
        StorageUtils.createFileIfNotExists(filePath);
    }

    /**
     * Assigns a unique ID to the article using UUID.randomUUID() and
     * then add it to the storage file.
     *
     * @param article the article to be added
     */
    @Override
    public void addArticle(Article article) {
        article.setId(UUID.randomUUID().toString());
        ArrayList<Article> existingArticles = getAllArticles();
        if(existingArticles == null) {
            existingArticles = new ArrayList<>();
        }
        existingArticles.add(article);
        saveArticles(existingArticles);
    }

    /**
     * Removes an article from the storage.
     *
     * @param article the article to be removed
     */
    @Override
    public void removeArticle(Article article) {
        ArrayList<Article> existingArticles = getAllArticles();
        existingArticles.remove(article);
        saveArticles(existingArticles);
    }

    /**
     * Retrieves all the articles stored in the storage.
     *
     * @return a list of all the articles in the storage
     */
    @Override
    public ArrayList<Article> getAllArticles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, new TypeToken<ArrayList<Article>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Retrieves the number of articles stored in the storage.
     *
     * @return the number of articles in the storage
     */
    @Override
    public int getArticleCount() {
        return getAllArticles().size();
    }

    /**
     * Checks if the storage contains the specified article.
     *
     * @param article the article to be checked
     * @return {@code true} if the storage contains the article, {@code false} otherwise
     */
    @Override
    public boolean containsArticle(Article article) {
        ArrayList<Article> articles = getAllArticles();
        return articles.contains(article);
    }

    /**
     * Checks if the storage is empty.
     *
     * @return {@code true} if the storage is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return getAllArticles().isEmpty();
    }

    /**
     * Clears the storage, removing all the articles.
     */
    @Override
    public void clearStorage() {
        saveArticles(new ArrayList<>());
    }

    /**
     * Saves the list of articles to the file storage.
     *
     * @param articles the list of articles to be saved
     */
    private void saveArticles(ArrayList<Article> articles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(articles));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
