package com.wind.ws.service;

import com.wind.ws.entity.Article;

import java.util.List;

public interface ArticleService {

    /**
     * 新建索引
     * @param article
     */
    void save(Article article);

    /**
     * 查询所有索引
     * @return
     */
    List<Article> findAll();


    /**
     * 根据条件查询索引
     * @param keyword
     * @return
     */
    List<Article> findByCondition(String keyword);

}
