package cn.itcast.travel.dao;

import cn.itcast.travel.domain.ArticleFavorite;

public interface ArticleFavoriteDao {
    ArticleFavorite findByAidAndUid(int aid,int uid);

    void add(int aid, int uid);

}
