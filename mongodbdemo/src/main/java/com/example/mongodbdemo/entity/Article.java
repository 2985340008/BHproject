package com.example.mongodbdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Article {

    @Id
    private Integer id;

    /*
       使用@Field 建立实体类属性与 collection 中字段的映射关系
       如果省略则是两名称一致
    */

    private String name;
    private String content;
    private Integer hist;
}
