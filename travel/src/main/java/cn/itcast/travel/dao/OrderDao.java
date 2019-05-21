package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Order;

import java.util.List;

public interface OrderDao{

    void save(Order order);

    void updateStage(Order order);

    void cancelStage(Order order);

    void finishPay(Order order);

    Order findByUid(int uid);

    Order findBySid(int sid);

    Order findByHid(int hid);

    List<Order> findPageByUid(int uid,int start,int pageSize);

    int findCountByUid(int uid);

    List<Order> findOrderByPage(int oid, int start , int pageSize);

    int findOrderCount(int oid);

}
