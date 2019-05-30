package cn.itcast.travel.web.servlet;
import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.HouseService;
import cn.itcast.travel.service.OrderService;
import cn.itcast.travel.service.SellerService;
import cn.itcast.travel.service.impl.HouseServiceImpl;
import cn.itcast.travel.service.impl.OrderServiceImpl;
import cn.itcast.travel.service.impl.SellerServiceImpl;
import cn.itcast.travel.util.DateComparisonUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

@WebServlet("/order/*")
public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();

    private HouseService houseService = new HouseServiceImpl();

    private SellerService sellerService = new SellerServiceImpl();

    public void createOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String startAndEndDate = request.getParameter("startAndEndDate");
        Map<String, String[]> map = request.getParameterMap();

        Order order = new Order();
        try {
            BeanUtils.populate(order,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        order.setUid(uid);

        String hid = request.getParameter("hid");
        House house = houseService.findOne(hid);
        String startDate = startAndEndDate.substring(0,10);
        String endDate = startAndEndDate.substring(13);
        DateComparisonUtil dateComparisonUtil = new DateComparisonUtil();
        int days = dateComparisonUtil.getDays(startDate,endDate)+1;
        System.out.println(days);
        double price =house.getPrice()*days;
//        System.out.println("startDate:"+startDate);
//        System.out.println("endDate:"+endDate);
//        int startYear = Integer.parseInt(startDate.substring(0,4));
//        int startMonth =Integer.parseInt(startDate.substring(5,7));
//        int startDay = Integer.parseInt(startDate.substring(8));
//        int endYear = Integer.parseInt(endDate.substring(0,4));
//        int endMonth =Integer.parseInt(endDate.substring(5,7));
//        int endDay = Integer.parseInt(endDate.substring(8));
//        int yearDiffer =endYear - startYear;
//        int monthDiffer = endMonth - startMonth;
//        int dayDiffer = endDay - startDay;


        order.setPrice(price);
        order.setSid(house.getSid());
        order.setHid(Integer.valueOf(hid));
        String name=new String(request.getParameter("name").getBytes("8859_1"), "utf8");
        order.setName(name);
        boolean flag = orderService.create(order);
        ResultInfo info = new ResultInfo();

        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("创建失败!");
        }

        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json数据写回客户端

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    public void myOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String uidStr = request.getParameter("uid");

        int uid = 0;//类别id

        if(uidStr != null && uidStr.length() > 0 && !"null".equals(uidStr)){
            uid = Integer.parseInt(uidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 10;
        }
        System.out.println("uid:"+uid);
        PageBean<Order> pb = orderService.userOrderPageQuery(uid,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);
    }

    public void allOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String oidStr = request.getParameter("oid");
        int oid = 0;//类别id

        if(oidStr != null && oidStr.length() > 0 && !"null".equals(oidStr)){
            oid = Integer.parseInt(oidStr);
        }

        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 10;
        }

        PageBean<Order> pb = orderService.allOrderPageQuery(oid,currentPage,pageSize);
        writeValue(pb,response);
    }


    public void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oidStr = request.getParameter("oid");
        int oid =Integer.parseInt(oidStr);
        Order order = orderService.findOneOrder(oid);
        boolean flag = orderService.cancel(order);
        ResultInfo info = new ResultInfo();

        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("退订失败");
        }

        writeValue(info,response);

    }

    public void payOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oidStr = request.getParameter("oid");
        int oid =Integer.parseInt(oidStr);
        Order order = orderService.findOneOrder(oid);
        boolean flag = orderService.endPay(order);
        ResultInfo info = new ResultInfo();

        if (flag){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("退订失败");
        }

        writeValue(info,response);
    }


    public void findOneOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oidStr = request.getParameter("oid");
        int oid = Integer.parseInt(oidStr);
        Order order = orderService.findOneOrder(oid);
        writeValue(order,response);
    }

    public void payMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oidStr = request.getParameter("oid");
        int oid = Integer.parseInt(oidStr);
        Order order = orderService.findOneOrder(oid);
        int sid = order.getSid();
        Seller seller = sellerService.findOneSeller(sid);
        List data = new ArrayList();
        data.add(oid);
        data.add(seller.getZfCode());
        data.add(seller.getWxCode());
        writeValue(data,response);

    }

}
