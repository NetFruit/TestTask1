package com.example.h2db.thread;

import com.example.h2db.Service.ArticleService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyThread extends Thread {
    private static String JsonString;
    private int limit;
    private int start;

    ArticleService articleService;

    public MyThread(){

    }
    public MyThread(ArticleService articleService,int limit,int start)
    {
        this.articleService = articleService;
        this.limit = limit;
        this.start = start;
    }



    public void run()
    {
        articleService.saveJsonToDb(JsonString,limit,start);
    }


}
