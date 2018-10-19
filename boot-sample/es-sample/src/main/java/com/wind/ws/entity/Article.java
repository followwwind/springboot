package com.wind.ws.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Date;


/**
 * @Document注解里面的几个属性，类比mysql的话是这样：
 * index –> DB  type –> Table  Document –> row
 * @Id注解加上后，在Elasticsearch里相应于该列就是主键了，在查询时就可以直接用主键查询
 * @author wind
 */
@Document(indexName="blog", type="article", shards=1, replicas=2, refreshInterval="-1")
public class Article implements Serializable {

    @Id
    private Long id;
    /**标题*/
    @Field
    private String title;
    /**摘要*/
    @Field
    private String abstracts;
    /**内容*/
    @Field
    private String content;
    /**发表时间*/
    private Date postTime;
    /**点击率*/
    private Long clickCount;
    /**作者*/
    private String author;
    /**所属教程*/
    @Field
    private String tutorial;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Long getClickCount() {
        return clickCount;
    }

    public void setClickCount(Long clickCount) {
        this.clickCount = clickCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }
}
