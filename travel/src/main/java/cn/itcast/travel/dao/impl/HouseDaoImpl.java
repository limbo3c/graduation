package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.HouseDao;
import cn.itcast.travel.domain.House;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class HouseDaoImpl implements HouseDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid,String hname) {
        //String sql = "select count(*) from tab_house where cid = ?";
        //1.定义sql模板
        String sql = "select count(*) from tab_house where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(cid != 0){
            sb.append( " and cid = ? ");

            params.add(cid);//添加？对应的值
        }

        if(hname != null && hname.length() > 0){
            sb.append(" and hname like ? ");

            params.add("%"+hname+"%");
        }

        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }


    @Override
    public int findCountBySid(int sid){
        String sql = "select count(*) from tab_house where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(sid != 0){
            sb.append( " and sid = ? ");

            params.add(sid);//添加？对应的值
        }
        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }


    @Override
    public List<House> findByPage(int cid, int start, int pageSize, String hname) {
        //String sql = "select * from tab_house where cid = ? and hname like ?  limit ? , ?";

        String sql = " select * from tab_house where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(cid != 0){
            sb.append( " and cid = ? ");

            params.add(cid);
        }

        if(hname != null && hname.length() > 0){
            sb.append(" and hname like ? ");

            params.add("%"+hname+"%");
        }
        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class),params.toArray());
    }

    @Override
    public House findOne(int hid) {
        String sql = "select * from tab_house where hid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<House>(House.class),hid);
    }


    @Override
    public void save(House house) {

        String sql = "insert into tab_house(hname,price,houseIntroduce,hflag,hdate,inChina,count,sid,peopleNumber,houseNumber,city,location,score,cid) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        template.update(sql,house.getHname(),
                house.getPrice(),
                house.getHouseIntroduce(),
                house.getHflag(),
                house.getHdate(),
                house.getInChina(),
                house.getCount(),
                house.getSid(),
                house.getPeopleNumber(),
                house.getHouseNumber(),
                house.getCity(),
                house.getLocation(),
                house.getScore(),
                house.getCid()
        );
    }



    @Override
    public House findByHname(String hname){
        House house = null;
        try {

            String sql = "select * from tab_house where hname = ?";

            house = template.queryForObject(sql, new BeanPropertyRowMapper<House>(House.class),hname);
        } catch (Exception e) {

        }

        return house;
    }

    //通过用户id查询房源列表
    @Override
    public List<House> findBySid(int sid,int start,int pageSize){
        String sql = " select * from tab_house where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(sid != 0){
            sb.append( " and sid = ? ");

            params.add(sid);
        }

        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql,new BeanPropertyRowMapper<House>(House.class),params.toArray());
    }

    //全部房源列表
    @Override
    public List<House> findAll(){
        String sql = "select * from tab_house";
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class));
    }


    @Override
    public void delete(int hid){
        String sql = "delete from tab_house where hid = ?";
        template.update(sql,hid);
    }

//    public static void main(String[] args) {
//        HouseDaoImpl houseDao = new HouseDaoImpl();
//        System.out.println(houseDao.findByPage(5, 0, 5, null));
//    }

    @Override
    public  List<House> findAllHouse(int start, int pageSize){
        String sql = " select * from tab_house ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql,new BeanPropertyRowMapper<House>(House.class),params.toArray());
    }

    @Override
    public int findHouseCount(){
        String sql = "select * from tab_house";
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class)).size();
    }


    @Override
    public void onSale(House house){
        int hid = house.getHid();
        String sql = " update tab_house set hflag = 1 where hid=?";
        template.update(sql,hid);
    }

    @Override
    public void offSale(House house){
        int hid = house.getHid();
        String sql = " update tab_house set hflag = 0 where hid=?";
        template.update(sql,hid);
    }

    @Override
    public House lastOne(){
        String sql = "select * from tab_house order by hid desc limit 1";
        return template.queryForObject(sql,new BeanPropertyRowMapper<House>(House.class));
    }


    @Override
    public void saveHimage(String himage,int hid){
        String sql = "update tab_house set himage = ? where hid = ?";
        template.update(sql,himage,hid);
    }

    @Override
    public List<House> hotHouse(){
        String sql = "select * from tab_house order by count desc limit 4";
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class));
    }

    @Override
    public List<House> newestHouse(){
        String sql = "select * from tab_house order by hdate desc limit 4";
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class));
    }

    @Override
    public List<House> testHouse(){
        String sql = "select * from tab_house order by score desc limit 4";
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class));
    }

    @Override
    public List<House> abroadRecommendHouse(){
        String sql = "select * from tab_house where inChina = 0 order by score desc  limit 6";
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class));
    }

    @Override
    public List<House> domesticRecommendHouse(){
        String sql = "select * from tab_house where inChina = 1 order by score desc  limit 6";
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class));
    }

    @Override
    public List<House> siderHouse(){
        String sql = "select * from tab_house order by score desc limit 5";
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class));
    }

    @Override
    public List<House> favoriteRankHouse(String hnameKey,int mixPrice,int maxPrice,int start,int pageSize){
        String sql = " select * from tab_house where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();


        if(mixPrice >= 0 && maxPrice>mixPrice){
            sb.append( " and price between ? and ? ");

            params.add(mixPrice);
            params.add(maxPrice);
        }

        if(hnameKey != null && hnameKey.length() > 0){
            sb.append(" and hname like ? ");

            params.add("%"+hnameKey+"%");
        }

        sb.append("order by count desc");

        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<House>(House.class),params.toArray());
    }

    @Override
    public int favoriteRankCount(String hnameKey,int mixPrice,int maxPrice){

        String sql = "select count(*) from tab_house where price>= "+mixPrice+" and price <="+maxPrice;
        System.out.println(sql);
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们

        if(hnameKey != null && hnameKey.length() > 0){
            sb.append(" and hname like ? ");

            params.add("%"+hnameKey+"%");
        }


        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public void decrement(int hid){
        String sql = "update tab_house set houseNumber=houseNumber - 1 where hid = ?";

        template.update(sql,hid);
        int houseNumber = findHouseNumber(hid);
        if (houseNumber <=0){
            lowerShelf(hid);
        }
    }

    @Override
    public void increment(int hid){
        String sql = "update tab_house set houseNumber=houseNumber + 1 where hid = ?";
        int houseNumber =  findHouseNumber(hid);
        if (houseNumber==0){
            upperShelf(hid);
        }
        template.update(sql,hid);
    }

    @Override
    public int findHouseNumber(int hid){
        String sql = "select houseNumber from tab_house where hid = ?";
        return template.queryForObject(sql,Integer.class,hid);
    }

    @Override
    public void lowerShelf(int hid){
        String sql = "update tab_house set hflag = 0 where hid = ?";
        template.update(sql,hid);

    }

    @Override
    public void upperShelf(int hid){
        String sql = "update tab_house set hflag = 1 where hid = ?";
        template.update(sql,hid);
    }

    @Override
    public void update(House house){
        String sql = "update tab_house set hname = ? , inChina = ? , city = ? , location = ? , houseIntroduce = ? , peopleNumber = ? , houseNumber = ? , price = ? , cid = ? , count = ?  , score = ? where hid = ?" ;


        template.update(sql,house.getHname(),
                house.getInChina(),
                house.getCity(),
                house.getLocation(),
                house.getHouseIntroduce(),
                house.getPeopleNumber(),
                house.getHouseNumber(),
                house.getPrice(),
                house.getCid(),
                house.getCount(),
                house.getScore(),
                house.getHid()
        );
    }

}
