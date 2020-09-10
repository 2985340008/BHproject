package com.example.esdemo;

import com.example.esdemo.dao.ArticleDao;
import com.example.esdemo.entity.Article;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:es.xml")
@SpringBootTest
public class EsTest {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ArticleDao articleDao;


    @Test

    public void testSave(){

        //创建索引
        template.createIndex(Article.class);
        //创建映射
        template.putMapping(Article.class);
        //创建文档
        Article article = new Article();
        article.setId(1);
        article.setTitle("sdu_title");
        article.setContext("stu_content");
        //保存文档
        articleDao.save(article);
    }

    @Test
    public void testFindAll(){
        Iterable<Article> all = articleDao.findAll();
        for (Article article : all) {
            System.out.println(article);
        }
    }
}