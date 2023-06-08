package it.unipd.eis;

import java.io.Serializable;

public class Article implements Serializable {

    private String id;
    private String title;
    private String bodyText;

    public Article(String id, String title, String bodyText) {
        this.id = id;
        this.title = title;
        this.bodyText = bodyText;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
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