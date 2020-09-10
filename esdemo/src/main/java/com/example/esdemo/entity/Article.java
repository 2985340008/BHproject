package com.example.esdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

//indexName:索引名称，type：类型
@Document(indexName = "sdu-sd",type = "article")
@Data
public class Article {

    @Id
    @Field(index = false,type = FieldType.Integer)
    private Integer id;
    /**
     * index:是否设置分词，默认为true
     * analyzer：存储时使用的分词器
     * searchAnalyzer：搜索时使用的分词器
     * store：是否存储，默认是false
     * type：数据类型，默认为FiledType.Auto
     */
    @Field(analyzer = "ik_smart",searchAnalyzer = "ik_smart",store = true,type = FieldType.text)
    private String title;

    @Field(analyzer = "ik_smart",searchAnalyzer = "ik_smart",store = true,type = FieldType.text)
    private String context;

    @Field(store = true,type = FieldType.Integer)
    private Integer hits;

}
