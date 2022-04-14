package com.example.h2db.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "ARTICLES")
public class Article  {

    @javax.persistence.Id
    @Column(name="id",nullable = false)
    long id;
    @Column(name="title")
    String title;
    @Column(name="newsSite")
    String newsSite;
    @Column(name="publishedAt")
    String publishedAt;
    @Column(name="text",length = 500)
    String summary;







    public  Article(){

    }

    public Article(long id,String title,String news_site,String published_date,String article_text)
    {
        this.id = id;
        this.title = title;
        this.newsSite = news_site;
        this.publishedAt = published_date;
        this.summary = article_text;
    }


    @Override
    public String toString()
    {
        return  id + " " + title +" "+ newsSite + " "+ publishedAt + " " + summary +"\n";
    }

/*
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNewsSite() {
        return newsSite;
    }

    public void setNewsSite(String newsSite) {
        this.newsSite = newsSite;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    */

}
