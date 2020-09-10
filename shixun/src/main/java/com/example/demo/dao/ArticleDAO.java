package com.example.demo.dao;

import com.example.demo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ArticleDAO extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

    //位置参数绑定--按 title、author 查询
    // /* 占位符是从 1 开始 JPQL:类似于 SQL 语句，需要使用实体类名代替表名，使得属性名代替字段名
    // （面向对象查询） */
    @Query("from Article a where a.title = ?1 and a.author = ?2")
    List<Article> findByCondition(String title, String author);

    //名字参数绑定
    @Query("from Article a where a.title = :titles and a.author = :author")
    List<Article> findByCondition2(@Param("titles") String title, @Param("author") String author);

    //like模糊查询
    @Query("from Article a where a.title like %:title%")
    List<Article> findByCondition3(@Param("title") String title);

    //排序查询
    @Query("from Article a where a.title like %:title% order by a.aid desc ")
    List<Article> findByCondition4(@Param("title") String title);

    //分页查询
    @Query("from Article a where a.title like %:title%")
    List<Article> findByCondition5(Pageable pageable, @Param("title") String title);

    //传入集合参数查询
    @Query("from Article a where a.aid in :aids")
    List<Article> findByCondition6(@Param("aids") List<Integer> aids);

    //传入Bean进行查询——SPEL(Sping表达式）表达式查询
    @Query("from Article a where a.title = :#{#article.title} and a.author = :#{#article.author}")
    List<Article> findByCondition7(@Param("article") Article article);

    //本地SQL查询
    @Query(value = "select * from article a where a.title = ?1 and a.author = ?2", nativeQuery = true)
    List<Article> findByCondition8(String title, String author);
}
