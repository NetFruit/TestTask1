package com.example.h2db.controller;

import com.example.h2db.Service.ArticleService;
import com.example.h2db.connection.MyConection;
import com.example.h2db.connection.MyParser;
import com.example.h2db.db.Article;
import com.example.h2db.repository.ArticleRepository;
import com.example.h2db.thread.MyThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "",produces = "application/json")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
/*
    @RequestMapping("download-all2")
    public List<Article> downloadDb2(String s) {
        ArticleService articleService = new ArticleService(articleRepository,10,10);
        int start=0;
        int limit=2000;
        int threadNumber=40;
        int oneThread = (limit-start)/threadNumber;
        String que="https://api.spaceflightnewsapi.net/v3/articles?_limit="+limit + "&_start="+start;
        MyConection myConection = new MyConection(limit,start);
        String json= myConection.getJsonString(que);



        articleRepository = articleService.getArticleRepository();
        return articleService.returnList();
    }
*/

    @RequestMapping("download-all")
    public List<Article> downloadDb(String s)
    {
        /*
        ArticleService articleService = new ArticleService(articleRepository, 100,1);
        //ArticleService articleService2 = new ArticleService(articleRepository, 100,51);
        DownloadThread downloadThread = new DownloadThread(articleService,100,1);
        //DownloadThread downloadThread2 = new DownloadThread(articleService2,100,51);
        //downloadThread2.start();
        downloadThread.start();
        */

        String query = "https://api.spaceflightnewsapi.net/v3/articles?_limit=50&_start=1";
        MyConection myConection  = new MyConection(50,1);
        String jsonString = myConection.getJsonString(query);
        //System.out.println(jsonString.toString());
        MyParser parser1=new MyParser();
        List<Article> articles= parser1.parse(jsonString);

        Comparator<Article> dateComporator = new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                try{
                    String date1 = o1.getPublishedAt();
                    String date2 = o2.getPublishedAt();

                    Date date11=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(date1);
                    Date date22=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(date2);

                    int result = date11.compareTo(date22);
                    if(result>0)
                        return 1;
                    else if(result<0)
                        return -1;
                    else
                        return 0;


                }catch(Exception e){
                    System.out.println(e);
                }
                return 0;
            }


        };
        articles.sort(dateComporator);


        return articleRepository.saveAll(articles);

        //System.out.println(articles);


       /* articleRepository = articleService.getArticleRepository();

        return articleService.returnList();
        */

    }

    @RequestMapping("download")
    public Article download1(String s)
    {

        String query = "https://api.spaceflightnewsapi.net/v3/articles?_limit=1&_start=1";
        MyConection myConection  = new MyConection(1,1);
        String jsonString = myConection.getJsonString(query);
        //System.out.println(jsonString.toString());
        MyParser parser1=new MyParser();
        List<Article> articles= parser1.parse(jsonString);
/*
        for(int i =0;i<articles.size()&&i<5;i++)
        {
            articleRepository.save(articles.get(i));
        }
*/
        return articleRepository.save(articles.get(0));
    }


    @RequestMapping("save-article")
    public Article saveArticle(Article article)
    {
        return articleRepository.save(article);
    }





}