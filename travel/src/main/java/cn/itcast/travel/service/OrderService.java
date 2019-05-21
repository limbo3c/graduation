package cn.itcast.travel.service;

import cn.itcast.travel.domain.Order;
import cn.itcast.travel.domain.PageBean;

public interface OrderService {

    boolean finish(Order order);

    boolean cancel(Order order);

    boolean endPay(Order order);

    boolean create(Order order);

    PageBean<Order> userOrderPageQuery(int uid, int currentPage, int pageSize);

    PageBean<Order> allOrderPageQuery(int oid,int currentPage, int pageSize);
}
