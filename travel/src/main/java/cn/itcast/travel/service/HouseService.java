package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.House;

import java.util.List;


public interface HouseService {

    //根据类别进行分页查询

    PageBean<House> pageQuery(int cid, int currentPage, int pageSize, String hname);

    PageBean<House> favoritePageQuery(List<Favorite> favorites, int currentPage, int pageSize);

    //根据id查询

    House findOne(String hid);

    House findByHname(String hname);

    boolean load(House house);

    List<House> findAllHouse();

    PageBean<House> userHousePageQuery(int uid,int currentPage,int pageSize);

    void deleteHouse(int hid);

    PageBean<House> allHousePageQuery( int currentPage, int pageSize);

    boolean onSale(int hid);

    boolean offSale(int hid);

    void saveHouseImg(int hid,String bigPic,String smallPic);

    House lastHouse();

    void saveHimage(String himage,int hid);

    //人气房源
    List<House> hotHouse();

    //最新房源
    List<House> newestHouse();

    List<House> testHouse();

    //境外推荐房源
    List<House> abroadRecommendHouse();

    //国内推荐房源
    List<House> domesticRecommendHouse();

    //搜索页面侧边栏热门推荐
    List<House> siderHouse();

    PageBean<House> favoriteRank(String hname,int mixPrice,int maxPrice,int currentPage,int pageSize);
}
