package com.wind.essample.service;

import com.wind.essample.entity.Article;

import java.util.List;

public interface IArticleService {

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
     * @return
     */
    List<Article> findByCondition(String keyword);

}
