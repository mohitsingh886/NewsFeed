package io.github.vishalecho.android.assignment.newsfeed.data.model;

import java.io.Serializable;
import java.util.List;

public class NewsFeedApiResponse implements Serializable {

    private String status;
    private List<Article> articles;

    /**
     * No args constructor for use in serialization
     */
    public NewsFeedApiResponse() {
    }

    /**
     * @param status
     * @param articles
     */
    public NewsFeedApiResponse(String status, List<Article> articles) {
        this.status = status;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
