package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;

import java.util.List;

public interface FavoriteService {

    //判断是否收藏

    public boolean isFavorite(String hid, int uid);

    //添加收藏

    void add(String hid, int uid);

    List<Favorite> myFavorite(int uid);
}
