package com.wind.jpa.dao;

import com.wind.jpa.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wind
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
}
