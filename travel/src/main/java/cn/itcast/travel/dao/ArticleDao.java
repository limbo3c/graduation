package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Article;

import java.util.List;

public interface ArticleDao {

    void save(Article article);

    void delete(int aid);

    List<Article> findArticle(int aid , int start , int pageSize, String title,String story);

    int findArticleCount(int aid , String title,String story);

    Article findOne(int aid);

    List<Article> hotArticle();



}
