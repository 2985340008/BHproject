package com.example.integration.service;

import com.example.integration.dao.CommentDao;
import com.example.integration.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    public CommentDao commentDao;
    //保存
    public void saveComment(Comment comment){
        commentDao.save(comment);
    }

    //删除
    public void deleteByCid(String cid){
        commentDao.deleteByCid(cid);
    }

    //根据文章标识查询评论数据
    public List<Comment> findCommentsByAid(Integer aid){
        return commentDao.findByAid(aid);
    }
}
