package com.example.h2db.controller;

import com.example.h2db.Service.ArticleService;
import com.example.h2db.connection.MyConection;
import com.example.h2db.connection.MyParser;
import com.example.h2db.db.Article;
import com.example.h2db.repository.ArticleRepository;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class DownloadThread extends Thread{

    ArticleService articleService;
    private static  String i;
    private int start;// общее количество записей
    private int limit;//количество статей скачиваемое одним потоком

    public DownloadThread(ArticleService articleService1,int limit,int start)
    {
        this.articleService = articleService1;
        this.limit = limit;
        this.start = start;
    }

    public void run()
    {
        articleService.saveToDb(limit,start);
    }
}
