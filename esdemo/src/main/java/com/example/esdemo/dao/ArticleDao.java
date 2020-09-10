package com.example.esdemo.dao;

import com.example.esdemo.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.awt.print.Pageable;
import java.util.List;

//@Repository
public interface ArticleDao extends ElasticsearchRepository<Article,Integer> {


    //根据标题查询
    List<Article> findByTitle(String title);

    //根据标题或内容查询
    List<Article> findByTitleOrContext(String title,String context);

    //根据标题或内容查询--带分页功能
    List<Article> findByTitleOrContext(String title, String context, Pageable pageable);

    List<Article> findByTitleAndContext(String title,String context);


}
