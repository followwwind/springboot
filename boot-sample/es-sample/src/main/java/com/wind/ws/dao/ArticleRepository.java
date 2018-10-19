package com.wind.ws.dao;

import com.wind.ws.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wind
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
}
