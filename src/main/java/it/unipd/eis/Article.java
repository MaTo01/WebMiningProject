package it.unipd.eis;

import java.io.Serializable;

/**
 * A class representing an article.
 */
public class Article implements Serializable {

    private String id;
    private String title;
    private String body;

    /**
     * Constructs an Article object with the specified title and body.
     *
     * @param title the title of the article
     * @param body  the body text of the article
     */
    public Article(String title, String body) {
        this.id = "";
        this.title = title;
        this.body = body;
    }

    /**
     * Returns the ID of the article.
     *
     * @return the ID of the article
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the title of the article.
     *
     * @return the title of the article
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the body text of the article.
     *
     * @return the body text of the article
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the ID of the article.
     *
     * @param id the ID of the article
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the title of the article.
     *
     * @param title the title of the article
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the body text of the article.
     *
     * @param bodyText the body text of the article
     */
    public void setBody(String bodyText) {
        this.body = bodyText;
    }

    /**
     * Checks if this article is equal to another object.
     *
     * @param obj the object to compare
     * @return true if the articles are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Article article = (Article) obj;
        return id.equals(article.id);
    }

}