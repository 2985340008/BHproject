package com.example.integration.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 数据库文章表
 */
@Entity
@Table(name="articledata",catalog = "project")
public class ArticleData implements Serializable {

    /**
     * 文章主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    /**
     * 文章内容
     */
    private String content;
    /**
     * 一对一关系，一篇文章数据属于一篇文章
     */
    @OneToOne
    @JoinColumn(name = "articleId" ,referencedColumnName = "aid",unique = true)
    private Article article;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
