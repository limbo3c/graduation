package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.*;
import cn.itcast.travel.dao.impl.*;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.HouseService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HouseServiceImpl implements HouseService {
    private HouseDao houseDao = new HouseDaoImpl();

    private HouseImgDao houseImgDao = new HouseImgDaoImpl();

    private SellerDao sellerDao = new SellerDaoImpl();

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public PageBean<House> pageQuery(int cid, int currentPage, int pageSize, String hname ) {


        PageBean<House> pb = new PageBean<House>();
        //当前页码
        pb.setCurrentPage(currentPage);
        //每页显示条数
        pb.setPageSize(pageSize);
        
        //总记录数
        int totalCount = houseDao.findTotalCount(cid,hname);
        pb.setTotalCount(totalCount);
        //当前页显示的数据集
        int start = (currentPage - 1) * pageSize;
        List<House> list = houseDao.findByPage(cid,start,pageSize,hname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;
    }




    @Override
    public PageBean<House>  userHousePageQuery(int uid,int currentPage,int pageSize){
        PageBean<House> pb = new PageBean<House>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        Seller seller = sellerDao.findByUid(uid);
        int sid = seller.getSid();

        int totalCount = houseDao.findCountBySid(sid);
        pb.setTotalCount(totalCount);
        System.out.println("total:"+totalCount);
        int start = (currentPage - 1) * pageSize;
        List<House> houses = houseDao.findBySid(sid,start,pageSize);
        pb.setList(houses);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public PageBean<House> favoritePageQuery(List<Favorite> favorites, int currentPage, int pageSize){
        PageBean<House> pb = new PageBean<House>();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = favorites.size();
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<House> houses = new ArrayList<>();
        House house;
        int hid;
        for (int i=0;i<favorites.size();i++){
            hid = favorites.get(i).getHid();
            house = houseDao.findOne(hid);
            houses.add(house);
        }
        pb.setList(houses);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public House findOne(String hid) {

        House house = houseDao.findOne(Integer.parseInt(hid));

        List<HouseImg> houseImgList = houseImgDao.findByHid(house.getHid());

        house.setHouseImgList(houseImgList);

        Seller seller = sellerDao.findById(house.getSid());
        house.setSeller(seller);

        //收藏次数
        int count = favoriteDao.findCountByHid(house.getHid());
        house.setCount(count);


        return house;
    }

    @Override
    public boolean load(House house) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        House u = houseDao.findByHname(house.getHname());

        if(u != null){

            return false;
        }
        Category category = categoryDao.findByCname(house.getCity());
        if (category==null){
            categoryDao.save(house.getCity());
            category = categoryDao.findByCname(house.getCity());
        }
        house.setCid(category.getCid());
        house.setScore(0);
        house.setHflag("1");
        house.setCount(0);
        house.setSid(1);
        house.setHdate(df.format(new Date()));

        houseDao.save(house);

        return true;
    }

    @Override
    public List<House> findAllHouse(){
        List<House> houses = houseDao.findAll();
        return houses;
    }

    @Override
    public void deleteHouse(int hid) {
        houseDao.delete(hid);

    }


    @Override
    public PageBean<House> allHousePageQuery( int currentPage, int pageSize){
        PageBean<House> pb = new PageBean<House>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = houseDao.findHouseCount();
        pb.setTotalCount(totalCount);
        System.out.println("total:"+totalCount);
        int start = (currentPage - 1) * pageSize;
        List<House> houses = houseDao.findAllHouse(start,pageSize);
        pb.setList(houses);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public boolean onSale(int hid){
        House house = houseDao.findOne(hid);
        if(house != null){
            houseDao.onSale(house);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean offSale(int hid){
        House house = houseDao.findOne(hid);
        if(house != null){
            houseDao.offSale(house);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void saveHouseImg(int hid,String bigPic,String smallPic){
        HouseImg houseImg = new HouseImg();
        houseImg.setHid(hid);
        houseImg.setBigPic(bigPic);
        houseImg.setSmallPic(smallPic);
        System.out.println("hid:"+houseImg.getHid());
        System.out.println("bigpic"+houseImg.getBigPic());
        houseImgDao.save(houseImg);
    }

    @Override
    public House findByHname(String hname){
        return houseDao.findByHname(hname);
    }

    @Override
    public House lastHouse(){
        return houseDao.lastOne();
    }

    @Override
    public void saveHimage(String himage,int hid){
        houseDao.saveHimage(himage,hid);
    }

    @Override
    public List<House> hotHouse(){
        return houseDao.hotHouse();
    }

    @Override
    public List<House> newestHouse(){
        return houseDao.newestHouse();
    }

    @Override
    public List<House> testHouse(){
        return houseDao.testHouse();
    }

    @Override
    public List<House> abroadRecommendHouse(){
        return houseDao.abroadRecommendHouse();
    }

    @Override
    public List<House> domesticRecommendHouse(){
        return houseDao.domesticRecommendHouse();
    }

    @Override
    public List<House> siderHouse(){
        return houseDao.siderHouse();
    }

    @Override
    public PageBean<House> favoriteRank(String hname,int mixPrice,int maxPrice,int currentPage,int pageSize){
        PageBean<House> pb = new PageBean<House>();
        //当前页码
        pb.setCurrentPage(currentPage);
        //每页显示条数
        pb.setPageSize(pageSize);

        //总记录数
        int totalCount = houseDao.favoriteRankCount(hname,mixPrice,maxPrice);
        System.out.println(totalCount);
        pb.setTotalCount(totalCount);
        //当前页显示的数据集
        int start = (currentPage - 1) * pageSize;
        List<House> list = houseDao.favoriteRankHouse(hname,mixPrice,maxPrice,start,pageSize);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;
    }
}
