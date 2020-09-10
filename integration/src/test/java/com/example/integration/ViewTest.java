package com.example.integration;

import com.example.integration.entity.Article;
import com.example.integration.entity.Comment;
import com.example.integration.entity.EsArticle;
import com.example.integration.service.ArticleService;
import com.example.integration.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ViewTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentService commentService;

    //最新文章列表
    @Test
    public void testFillNewArticleList(){
        List<Article> list = articleService.findNewArticleList();
        for (Article article : list){
            System.out.println(article);
        }
    }

    //根据文章获取评论
    @Test
    public void testFindCommentByAid(){
        List<Comment> comments = commentService.findCommentsByAid(8);
        for (Comment comment : comments){
            System.out.println(comment);
        }
    }

    //文章全文检索
    @Test
    public void testSearch(){
        List<EsArticle> esArticles = articleService.search(0,10,"文章");
        for (EsArticle esArticle : esArticles){
            System.out.println(esArticle);
        }
    }
}
