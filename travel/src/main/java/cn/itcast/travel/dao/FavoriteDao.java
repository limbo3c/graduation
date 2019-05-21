package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

import java.util.List;

public interface FavoriteDao {

   // 根据hid和uid查询收藏信息

    Favorite findByHidAndUid(int hid, int uid);

    //根据hid 查询收藏次数

    int findCountByHid(int hid);

    //添加收藏
    void add(int i, int uid);

    //查用户收藏
    List<Favorite> findByUid(int uid);
}
