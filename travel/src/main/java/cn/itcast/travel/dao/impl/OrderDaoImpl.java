package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.OrderDao;
import cn.itcast.travel.domain.Order;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    //保存订单
    @Override
    public void save(Order order){
        String sql = "insert into tab_order(hid,sid,uid,price,telephone,name,identity,startAndEndDate,pay,stage,createDate,updateDate) values(?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,order.getHid(),
                order.getSid(),
                order.getUid(),
                order.getPrice(),
                order.getTelephone(),
                order.getName(),
                order.getIdentity(),
                order.getStartAndEndDate(),
                order.getPay(),
                order.getStage(),
                order.getCreateDate(),
                order.getUpdateDate());

    }
    //完结订单
    @Override
    public void updateStage(Order order){
        String sql = " update tab_order set stage = 2 where oid=?";
        template.update(sql,order.getOid());
    }
    //退订
    @Override
    public void cancelStage(Order order){
        String sql = " update tab_order set stage = 3 where oid=?";
        template.update(sql,order.getOid());
    }

    @Override
    public void finishPay(Order order){
        String sql = " update tab_order set pay = 2 where oid=?";
        template.update(sql,order.getOid());
    }


    @Override
    public Order findByUid(int uid){
        Order order = null;
        try {
            String sql = "select * from tab_order where uid = ?";

            order = template.queryForObject(sql,new BeanPropertyRowMapper<Order>(Order.class),uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public Order findBySid(int sid){
        Order order = null;
        try {
            String sql = "select * from tab_order where sid = ?";

            order = template.queryForObject(sql,new BeanPropertyRowMapper<Order>(Order.class),sid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return order;
    }


    @Override
    public Order findByHid(int hid){
        Order order = null;
        try {
            String sql = "select * from tab_order where hid = ?";

            order = template.queryForObject(sql,new BeanPropertyRowMapper<Order>(Order.class),hid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return order;
    }


    @Override
    public List<Order> findPageByUid(int uid, int start, int pageSize){
        String sql = " select * from tab_order where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(uid != 0){
            sb.append( " and uid = ? ");

            params.add(uid);
        }

        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),params.toArray());
    }

    @Override
    public int findCountByUid(int uid){
        String sql = "select count(*) from tab_order where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(uid != 0){
            sb.append( " and uid = ? ");

            params.add(uid);//添加？对应的值
        }
        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }


    @Override
    public  List<Order> findOrderByPage(int oid , int start , int pageSize){

        String sql = " select * from tab_order where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(oid != 0){
            sb.append( " and oid = ? ");

            params.add(oid);
        }

        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Order>(Order.class),params.toArray());
    }

    @Override
    public int findOrderCount(int oid ){
        String sql = "select count(*) from tab_order where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(oid != 0){
            sb.append( " and oid = ? ");

            params.add(oid);//添加？对应的值
        }
        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

}
