package cn.itcast.travel.service;

import cn.itcast.travel.domain.Article;
import cn.itcast.travel.domain.ArticleFavorite;
import cn.itcast.travel.domain.PageBean;

import java.util.List;

public interface ArticleService {

    boolean createArticle(Article article);

    boolean deleteArticle(int aid);

    PageBean<Article> articleQuery(int aid, int currentPage, int pageSize, String title,String story);

    List<Article> hotArticle();

    Article findOneArticle(int aid);

    void increaseFabulous(int aid);

    void decreaseFabulous(int aid);

    PageBean<Article> myArticle(int author,int currentPage,int pageSize);

    PageBean<Article> myFavoriteArticle(List<ArticleFavorite> articleFavorites,int currentPage,int pageSize);

    PageBean<Article> articleManageQuery(String authorName,String title,int currentPage,int pageSize);

    boolean updateFabulous(int aid,int fabulous);

}
