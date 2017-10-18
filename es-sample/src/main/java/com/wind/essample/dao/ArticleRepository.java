package com.wind.essample.dao;

import com.wind.essample.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author wind
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {
}
