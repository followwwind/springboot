package com.wind.ws.service.impl;

import com.wind.ws.dao.ArticleRepository;
import com.wind.ws.entity.Article;
import com.wind.ws.service.ArticleService;
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
public class ArticleServiceImpl implements ArticleService {

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
