package cn.itcast.travel.dao;

import cn.itcast.travel.domain.ArticleFavorite;

import java.util.List;

public interface ArticleFavoriteDao {
    ArticleFavorite findByAidAndUid(int aid,int uid);

    void add(int aid, int uid);

    List<ArticleFavorite> findByUid(int uid);

}
