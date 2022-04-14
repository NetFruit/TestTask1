package com.example.h2db.controller;

import com.example.h2db.db.Article;
import com.example.h2db.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/main")
    public String getArticles(Map<String,Object> model)
    {
        Iterable<Article> articles = articleRepository.findAll();
        model.put("articles",articles);
        return "main";
    }

    @GetMapping
    public String getArticle(Map<String,Object> model)
    {
        Iterable<Article> articles = articleRepository.findAll();
        model.put("articles",articles);
        return "articles";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,Map<String,Object> model)
    {
        Iterable<Article> articles;
        if(filter !=null && filter.isEmpty())
        {
            articles = articleRepository.findByNewsSite(filter);
        }else
        {
            articles = articleRepository.findAll();
        }


        model.put("articles",articles);
        return "articles";
    }


   @GetMapping(path="articles/{id}")
    public String findArcticle(@PathVariable Long id,Model model)
   {
       Article article = articleRepository.getById(id);
       model.addAttribute("article",article);

       return "information";
   }


}
