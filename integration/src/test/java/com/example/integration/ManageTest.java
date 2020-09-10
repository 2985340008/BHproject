package com.example.integration;

import com.example.integration.entity.Article;
import com.example.integration.entity.ArticleData;
import com.example.integration.entity.Comment;
import com.example.integration.service.ArticleService;
import com.example.integration.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ManageTest {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    //向MySQL中保存文章及文章内容
    @Test
    public void testSaveArticle(){
        ArticleData articleData = new ArticleData();
        articleData.setContent("这是一篇文章");
        Article article = new Article();
        article.setTitle("文章标题-4 月 21 日");
        article.setAuthor("文章作者-4 月 21 日");
        article.setCreateTime(new Date());

        //建立两者关系
        article.setArticleData(articleData);
        articleData.setArticle(article);
        articleService.saveArticle(article);
    }

    @Test
    public void testUpdateArticle(){
        //准备数据
        ArticleData articleData = new ArticleData();
        articleData.setContent("测试");
        Article article = new Article();
        article.setAid(4);
        article.setTitle("测试");
        article.setAuthor("测试");
        article.setCreateTime(new Date());
        article.setArticleData(articleData);
        articleService.updateArticle(article);
    }

    //删除文章及内容
    @Test
    public void testDeleteArticle(){
        articleService.deleteByAid(3);
    }

//    添加评论
    @Test
    public void testSaveComment(){
        Comment comment = new Comment();
        comment.setCid(UUID.randomUUID().toString());
        comment.setAid(8);
        comment.setComment("这是第n+1条评论信息...");
        comment.setNickname("第n+1条评论者的昵称...");
        commentService.saveComment(comment);
    }

    //删除评论
    @Test
    public void deleteByCid(){
        commentService.deleteByCid("af7127bc-a971-4951-9aa4-bbf7f1650085");
    }
}
