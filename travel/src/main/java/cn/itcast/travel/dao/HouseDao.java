package cn.itcast.travel.dao;

import cn.itcast.travel.domain.House;


import java.util.List;

public interface HouseDao {

    //根据cid查询总记录数

    int findTotalCount(int cid,String hname);

    // 根据cid，start,pageSize查询当前页的数据集合

    List<House> findByPage(int cid , int start , int pageSize, String hname);

    //根据id查询

    House findOne(int hid);

    void save(House house);

    House findByHname(String hname);

    List<House> findAll();

    List<House> findBySid(int sid,int start,int pageSize);

    int findCountBySid(int sid);

    void delete(int hid);

    List<House> findAllHouse(int start, int pageSize);

    int findHouseCount();

    void onSale(House house);

    void offSale(House house);

//    void saveImage(House house);

    House lastOne();

    void saveHimage(String himage,int hid);

    //人气房源
    List<House> hotHouse();

    //最新房源
    List<House> newestHouse();

    //评分推荐房源
    List<House> testHouse();

    //境外推荐房源
    List<House> abroadRecommendHouse();

    //国内推荐房源
    List<House> domesticRecommendHouse();

    //搜索页面侧边栏热门推荐
    List<House> siderHouse();

    List<House> favoriteRankHouse(String hnameKey,int mixPrice,int maxPrice,int start,int pageSize);

    int favoriteRankCount(String hnameKey,int mixPrice,int maxPrice);

}
