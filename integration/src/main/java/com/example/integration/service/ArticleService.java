package com.example.integration.service;

import com.alibaba.fastjson.JSONObject;
import com.example.integration.dao.ArticleDao;
import com.example.integration.dao.ArticleDataDao;
import com.example.integration.dao.CommentDao;
import com.example.integration.dao.EsArticleDao;
import com.example.integration.entity.Article;
import com.example.integration.entity.Comment;
import com.example.integration.entity.EsArticle;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ArticleService {


    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleDataDao articleDataDao;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private EsArticleDao esArticleDao;
    @Autowired
    private CommentDao commentDao;

    //向MySQL中保存文章及文章内容
    public void saveArticle(Article article){
        //正确的数据保存顺序
        articleDao.save(article);
        articleDataDao.save(article.getArticleData());
        //清空redis缓存
        redisTemplate.delete("article");
        //向 es 中保存数据
        EsArticle esArticle = new EsArticle();
        esArticle.setCreateTime(article.getCreateTime());
        esArticle.setContent(article.getArticleData().getContent());
        esArticle.setTitle(article.getTitle());
        esArticle.setAuthor(article.getAuthor());
        esArticle.setId(article.getAid());
        esArticleDao.save(esArticle);
    }

    //更新MySQL中保存的文章及文章内容
    public void updateArticle(Article article){
        //更新article
        Article articleParam = new Article();
        articleParam.setTitle(article.getTitle());
        articleParam.setAid(article.getAid());
        articleParam.setAuthor(article.getAuthor());
        articleParam.setCreateTime(article.getCreateTime());
        articleDao.save(articleParam);
        //更新articleData
        articleDataDao.updateContentByAid(article.getArticleData().getContent(),article.getAid());
        //清空redis缓存
        redisTemplate.delete("article");
        //向ES中保存数据
        EsArticle esArticle =new EsArticle();
        esArticle.setId(article.getAid());
        esArticle.setCreateTime(article.getCreateTime());
        esArticle.setContent(article.getArticleData().getContent());
        esArticle.setTitle(article.getTitle());
        esArticle.setAuthor(article.getAuthor());
        esArticleDao.save(esArticle);
    }
    //删除文章及文章内容
    public void deleteByAid(Integer aid){
        //删除articleData
        articleDataDao.deleteByAid(aid);
        //删除article
        articleDao.deleteById(aid);
        //删除mongodb中的相关评论
        /*
          a、首先根据 aid 查询一个 comment 列表
          b、删除该评论列表
        */
        List<Comment> comments = commentDao.findByAid(aid);
        commentDao.deleteAll(comments);

        redisTemplate.delete("srticle");

        esArticleDao.deleteById(aid);
    }

    //更新文章列表
    public List<Article> findNewArticleList(){
        //从redis中获取
        String value = redisTemplate.opsForValue().get("article");
        //如果redis中没有，到数据库查询，并把结果存入redis
        if (StringUtils.isEmpty(value)){
            //分页排序条件
            Pageable pageable = PageRequest.of(0,10,Sort.by(Sort.Order.desc("createTime")));
            Page<Article> page = articleDao.findAll(pageable);
            List<Article> content = page.getContent();
            if (content != null && content.size() > 0){
                value  = JSONObject.toJSONString(content);
                redisTemplate.opsForValue().set("article",value);
            }
        }
        //把结果转成List返回
        return JSONObject.parseArray(value,Article.class);
    }
    //文章检索
    public List<EsArticle> search(Integer pageNum,Integer pageSize,String keyword){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esArticleDao.findByTitleOrContent(keyword,keyword,pageable);
    }
}