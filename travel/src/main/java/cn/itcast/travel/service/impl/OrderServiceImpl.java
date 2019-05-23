package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.HouseDao;
import cn.itcast.travel.dao.OrderDao;
import cn.itcast.travel.dao.impl.HouseDaoImpl;
import cn.itcast.travel.dao.impl.OrderDaoImpl;
import cn.itcast.travel.domain.Order;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl() ;

    private HouseDao houseDao = new HouseDaoImpl();

    //完结订单
    @Override
    public boolean finish(Order order){
        if(order != null){
            orderDao.updateStage(order);
            return true;
        }else {
            return false;
        }

    }

    //取消订单
    @Override
    public boolean cancel(Order order){
        if (order != null){
            orderDao.cancelStage(order);
            return true;
        }else {
            return false;
        }

    }

    //支付订单
    @Override
    public  boolean endPay(Order order){
        if (order != null){
            orderDao.finishPay(order);
            houseDao.increment(order.getHid());
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean create(Order order) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        order.setPay(0);

        order.setStage(1);

        order.setCreateDate(df.format(new Date()));

        order.setUpdateDate(df.format(new Date()));

        orderDao.save(order);

        houseDao.decrement(order.getHid());

        return true;
    }


    @Override
    public PageBean<Order> userOrderPageQuery(int uid, int currentPage, int pageSize){
        PageBean<Order> pb = new PageBean<Order>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);


        int totalCount = orderDao.findCountByUid(uid);
        pb.setTotalCount(totalCount);
        System.out.println("total:"+totalCount);
        int start = (currentPage - 1) * pageSize;
        List<Order> orders = orderDao.findPageByUid(uid,start,pageSize);
        pb.setList(orders);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public PageBean<Order> allOrderPageQuery(int oid,int currentPage, int pageSize){
        PageBean<Order> pb = new PageBean<Order>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);


        int totalCount = orderDao.findOrderCount(oid);
        pb.setTotalCount(totalCount);
        System.out.println("total:"+totalCount);
        int start = (currentPage - 1) * pageSize;
        List<Order> orders = orderDao.findOrderByPage(oid,start,pageSize);
        pb.setList(orders);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

}
