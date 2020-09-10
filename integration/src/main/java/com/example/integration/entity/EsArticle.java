package com.example.integration.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(indexName = "project", type = "article")
public class EsArticle {
    @Id
    @Field(type = FieldType.Integer)
    private Integer id;
    @Field(type = FieldType.text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart", store = true)
    private String title;
    @Field(type = FieldType.text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart", store = true)
    private String content;
    @Field(type = FieldType.text)
    private String author;
    @Field(type = FieldType.Date)
    private Date createTime;
}
