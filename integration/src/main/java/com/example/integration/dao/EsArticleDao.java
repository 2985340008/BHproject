package com.example.integration.dao;

import com.example.integration.entity.EsArticle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.List;

public interface EsArticleDao extends ElasticsearchRepository<EsArticle,Integer> {
    //根据title或content查询
    List<EsArticle> findByTitleOrContent(String title, String content, Pageable pageable);
}
