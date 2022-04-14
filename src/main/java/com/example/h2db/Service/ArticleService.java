package com.example.h2db.Service;

import com.example.h2db.connection.MyConection;
import com.example.h2db.connection.MyParser;
import com.example.h2db.db.Article;
import com.example.h2db.repository.ArticleRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    private String connection;
    private int limit;
    private int start;

    public ArticleService(ArticleRepository articleRepository1,int limit, int start)
    {
        this.articleRepository = articleRepository1;
        connection="https://api.spaceflightnewsapi.net/v3/articles?_limit="+limit + "&_start="+start;
        this.limit = limit;
        this.start = start;
    }
    public ArticleService(String connection)
    {
        this.connection=  connection;
    }

    public synchronized void saveToDb(int limit,int start)
    {
        connection="https://api.spaceflightnewsapi.net/v3/articles?_limit="+limit + "&_start="+start;
        MyConection myConection  = new MyConection(limit,start);
        String jsonString = myConection.getJsonString(connection);
        //System.out.println(jsonString.toString());
        MyParser parser1=new MyParser();
        List<Article> articles= parser1.parse(jsonString);


        //System.out.println(articles);

        articleRepository.saveAll(articles);

    }

    public List<Article> returnList()
    {
        return articleRepository.findAll();
    }


    public void sortByPublishedAt()
    {/*
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
        */
    }
    // метод который будет выполнятся в MyThread
    public void saveJsonToDb(String json, int limit,int start)
    {
        MyParser parser1=new MyParser();
        List<Article> articles= parser1.parsePart(json,limit,start);
        articleRepository.saveAll(articles);

    }
}
