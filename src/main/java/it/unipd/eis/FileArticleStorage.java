package it.unipd.eis;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class FileArticleStorage implements ArticleStorage {
    private final String filePath;

    public FileArticleStorage(String filePath) {
        this.filePath = filePath;
        createDirectoryIfNotExists();
    }
    @Override
    public void addArticle(Article article) {
        String articleFilePath = getArticleFilePath(article.getId());
        try (PrintWriter writer = new PrintWriter(new FileWriter(articleFilePath))) {
            writer.println(article.getTitle());
            writer.println(article.getBodyText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeArticle(Article article) {
        String articleFilePath = getArticleFilePath(article.getId());
        try {
            Files.deleteIfExists(Paths.get(articleFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                Article article = readArticleFromFile(file);
                if (article != null) {
                    articles.add(article);
                }
            }
        }
        return articles;
    }

    @Override
    public int getArticleCount() {
        return getAllArticles().size();
    }

    @Override
    public boolean containsArticle(Article article) {
        String articleFilePath = getArticleFilePath(article.getId());
        File file = new File(articleFilePath);
        return file.exists();
    }

    @Override
    public boolean isEmpty() {
        return getAllArticles().isEmpty();
    }

    @Override
    public void clearStorage() {
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    private String getArticleFilePath(String articleId) {
        return filePath + "/" + articleId + ".txt";
    }

    private Article readArticleFromFile(File file) {
        String articleId = file.getName().replace(".txt", "");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String title = reader.readLine();
            String body = reader.readLine();
            return new Article(articleId, title, body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void createDirectoryIfNotExists() {
        Path directoryPath = Paths.get(filePath);
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}