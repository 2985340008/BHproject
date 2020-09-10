package com.example.integration.dao;

import com.example.integration.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentDao extends MongoRepository<Comment,String> {
    //按 aid 查询到一个文章的所有评论---命名规则查询
    @Query
    List<Comment> findByAid(Integer aid);

    @Query
    void deleteByCid(String cid);
}
