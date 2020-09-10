package com.example.mongodbdemo;

import com.example.mongodbdemo.dao.ArticleDao;
import com.example.mongodbdemo.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ArticleDaoTest {
    @Autowired
    private ArticleDao articleDao;

    //保存
    @Test
    public void testSave(){
        Article article = new Article();
        article.setId(2);
        article.setName("test-name2");
        article.setContent("test-content2");
        article.setHist(10);
        articleDao.save(article);
    }

    //保存
//    @Test
//    public void testSave() {
//        Article article = new Article();
//        article.setId(13);
//        article.setName("樱花");
//        article.setContent("樱花");
//        article.setHist(100);
//        articleDao.save(article);
//    }
    //删除
    @Test
    public void testDelete(){
        articleDao.deleteById(2);
    }

    //添加系列数据
    @Test
    public void addData(){
        for (int i = 3;i<11;i++){
            Article article = new Article();
            article.setId(i);
            article.setName("adu_name:"+i);
            article.setContent("sdu_content:"+i);
            article.setHist(100+i);
            articleDao.save(article);
        }
    }

    //查询所有
    @Test
    public void testFindAll(){
        List<Article> articles = articleDao.findAll();
        for (Article article : articles){
            System.out.println(article);
        }
    }

    //主键查询
    @Test
    public void testFindById(){
        Optional<Article> optional = articleDao.findById(1);
        System.out.println(optional);
    }

    //排序 分页
    @Test
    public void testFindAllWithPageAndSort(){
        //排序条件
        Sort sort = Sort.by(Sort.Order.desc("hits"));
        //分页条件
        Pageable pageable = PageRequest.of(1,3,sort);
        Page<Article> page = articleDao.findAll(pageable);
        for (Article article : page.getContent()){
            System.out.println(article);
        }
    }

    /**
     * 自定义接口查询
     */
    //根据标题查询
    @Test
    public void testFindByName(){
        List<Article> articles = articleDao.findByNameLike("adu");
        for (Article article : articles){
            System.out.println(article);
        }
    }

    //根据点击量查询
    @Test
    public void testFindByHitsGreaterThan(){
        List<Article> articles = articleDao.findByHistGreaterThan(105);
        for (Article article : articles){
            System.out.println(article);
        }
    }
}
