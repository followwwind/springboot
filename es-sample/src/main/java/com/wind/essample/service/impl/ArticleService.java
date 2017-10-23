package com.wind.essample.service.impl;

import com.wind.essample.dao.ArticleRepository;
import com.wind.essample.entity.Article;
import com.wind.essample.service.IArticleService;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author wind
 */
@Service
public class ArticleService implements IArticleService{

    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public void save(Article article) {
        article.setPostTime(new Date());
        articleRepository.save(article);
    }

    @Override
    public List<Article> findAll() {
        return null;
    }

    @Override
    public List<Article> findByCondition(String keyword) {

        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(keyword);
        Iterable<Article> searchResult = articleRepository.search(builder);
        Iterator<Article> iterator = searchResult.iterator();
        List<Article> articles = new ArrayList<>();
        while (iterator.hasNext()) {
            articles.add(iterator.next());
        }
        return articles;
    }
}
