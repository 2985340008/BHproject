package com.example.integration.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Data
@Document(collection = "comment")
public class Comment {
    @Id
    private String cid;//主键
    private Integer aid;//文章标识
    private String comment;//评论内容
    private String nickname;//评论者昵称
}
