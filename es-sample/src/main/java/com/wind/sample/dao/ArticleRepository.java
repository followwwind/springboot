package com.wind.sample.dao;

import com.wind.sample.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wind
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
}
