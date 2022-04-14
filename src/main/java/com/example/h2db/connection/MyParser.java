package com.example.h2db.connection;

import com.example.h2db.db.Article;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyParser {

    public List<Article> parse(String responseBody){



        JSONArray items = new JSONArray(responseBody);

        List<Article> articleList=new ArrayList<Article>();
        for(Object it:items )
        {


                JSONObject articleJsonObject = (JSONObject) it;
                String title = (String) articleJsonObject.get("title");
                int id = (int) articleJsonObject.get("id");
                String newsSite =(String) articleJsonObject.get("newsSite");
                String publishedAt = (String) articleJsonObject.get("publishedAt");
                String summary = (String) articleJsonObject.get("summary");
                publishedAt = publishedAt.replace('Z',' ');
                publishedAt = publishedAt.replace('T',' ');




                Article article = new Article((long)id,title,newsSite,publishedAt,summary);
                articleList.add(article);




        }


        return articleList;

    }

    public List<Article> parsePart(String json,int limit,int start)
    {

        JSONArray items = new JSONArray(json);

        List<Article> articleList=new ArrayList<Article>();
        for(int i = start; (i < limit) && i<items.length(); i++ )
        {


            JSONObject articleJsonObject = (JSONObject) items.get(i);
            String title = (String) articleJsonObject.get("title");
            int id = (int) articleJsonObject.get("id");
            String newsSite =(String) articleJsonObject.get("newsSite");
            String publishedAt = (String) articleJsonObject.get("publishedAt");
            String summary = (String) articleJsonObject.get("summary");
            publishedAt = publishedAt.replace('Z',' ');
            publishedAt = publishedAt.replace('T',' ');




            Article article = new Article((long)id,title,newsSite,publishedAt,summary);
            articleList.add(article);




        }


        return articleList;
    }


}