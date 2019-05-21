package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

import java.util.List;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public boolean isFavorite(String hid, int uid) {

        Favorite favorite = favoriteDao.findByHidAndUid(Integer.parseInt(hid), uid);

        return favorite != null;//如果对象有值，则为true，反之，则为false
    }

    @Override
    public void add(String hid, int uid) {
        favoriteDao.add(Integer.parseInt(hid),uid);
    }

    @Override
    public List<Favorite> myFavorite(int uid){
        List<Favorite> favorites = favoriteDao.findByUid(uid);

        return favorites;
    }
}
