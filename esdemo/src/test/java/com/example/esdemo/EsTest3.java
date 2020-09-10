package com.example.esdemo;

import com.example.esdemo.dao.ArticleDao;
import com.example.esdemo.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:es.xml")
@SpringBootTest
public class EsTest3 {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void makeData(){
        //创建索引
        template.createIndex(Article.class);
        //创建映射
        template.putMapping(Article.class);
        for (int i = 1;i<=10;i++){
            //创建文档
            Article article = new Article();
            article.setId(i);
            article.setTitle("stu_title:"+ i);
            article.setContext("sdu_context:" + i);
            article.setHits(100 + i);
            //保存
            articleDao.save(article);
        }
    }

    //根据标题查询
    @Test
    public void testFindByTitle(){
        List<Article> articles = articleDao.findByTitle("sdu_title");
        for (Article article : articles){
            System.out.println(article);
        }
    }
}
