package com.example.integration.dao;

import com.example.integration.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ArticleDao extends JpaRepository<Article,Integer>,JpaSpecificationExecutor<Article> {
}
