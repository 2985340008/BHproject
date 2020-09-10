package com.example.esdemo;

import com.example.esdemo.dao.ArticleDao;
import com.example.esdemo.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EsTest2 {

    @Autowired
    private ElasticsearchTemplate template;

    @Autowired
    private ArticleDao articleDao;

    //添加或修改
    @Test
    public void testUpdate(){
        /**
         * 首先根据id判断是否存在
         * 不存在则添加
         * 存在则修改
         */
        Article article = new Article();
        article.setId(4);
        article.setHits(102);
        article.setTitle("stu_title:2");
        article.setContext("sdu_count:222");
        articleDao.save(article);
    }

    //删除
    @Test
    public void testDelete(){
        //根据主键删除
        articleDao.deleteById(2);
    }

    //查询所有
    @Test
    public void testFindAll(){
        Iterable<Article> all = articleDao.findAll();
        for (Article article : all){
            System.out.println(article);
        }
    }

    //主键查询
    @Test
    public void testFindById(){
        Optional<Article> optional = articleDao.findById(1);
        System.out.println(optional.get());
    }

    //分页查询
    @Test
    public void testFindAllWithPage(){
        //分页条件  page:页码，从0开始
        PageRequest pageable = PageRequest.of(1,3);
        Page<Article> page = articleDao.findAll(pageable);
        for (Article article : page.getContent()){
            System.out.println(article);
        }
    }

    //查询+排序
    @Test
    public void testFindAndSort(){
        //排序条件
        Sort sort = Sort.by(Sort.Order.desc("hits"));
        //分页条件
        PageRequest pageable = PageRequest.of(0,4,sort);
        Page<Article> page = articleDao.findAll(pageable);
        for (Article article : page.getContent()){
            System.out.println(article);
        }
    }

    //根据标题查询
    @Test
    public void TestFindByTitle(){
        List<Article> list= articleDao.findByTitle("sdu_title");
        for (Article a:
             list) {
            System.out.println(a);
        }

    }

    //根据标题或内容查询--实现分页
//    @Test
//    public void testFindByTitleOrContextWithPage(){
//        //排序条件
//        Sort sort = Sort.by(Sort.Order.desc("hits"));
//        //分页条件
//        PageRequest pageable = PageRequest.of(1,3,sort);
//        List<Article> articles = articleDao.findByTitleOrContext("sdu_title","sdu",pageable);
//        for (Article article : articles){
//            System.out.println(article);
//        }
//    }



    @Test
    public void findByTileAndContext(){

        List<Article> list = articleDao.findByTitleAndContext("sdu", "ooo");
        for (Article a:list ) {
            System.out.println(a);
        }
    }
}

