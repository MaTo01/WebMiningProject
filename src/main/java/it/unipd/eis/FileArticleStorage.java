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
    private final String FILE_PATH;

    /**
     * Constructs a new instance of {@code FileArticleStorage} with the specified file path.
     *
     * @param dirPath the path of the directory used for storing the articles file
     */
    public FileArticleStorage(String dirPath) {
        StorageUtils.createDirectoryIfNotExists(dirPath);
        this.FILE_PATH = dirPath + "/articles.json";
        StorageUtils.createFileIfNotExists(FILE_PATH);
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
     * Adds a list of articles to the system.
     * Each article is assigned a unique ID generated using UUID.randomUUID().
     * If there are existing saved articles, the new articles are added to the existing list.
     * If there are no saved articles, a new list is created.
     * Finally, the list of articles is added to the storage file.
     *
     * @param articles the list of articles to add
     */
    @Override
    public void addArticles(List<Article> articles) {
        for(Article a : articles) {
            a.setId(UUID.randomUUID().toString());
        }
        ArrayList<Article> existingArticles = getAllArticles();
        if(existingArticles == null) {
            existingArticles = new ArrayList<>();
        }
        existingArticles.addAll(articles);
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
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(articles));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
