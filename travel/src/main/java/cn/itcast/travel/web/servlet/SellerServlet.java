package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.SellerService;
import cn.itcast.travel.service.impl.SellerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/seller/*")
public class SellerServlet extends BaseServlet {

    private SellerService sellerService = new SellerServiceImpl();

    public void becomeSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();

        //封装对象
        Seller seller = new Seller();

        try {
            BeanUtils.populate(seller,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }

        seller.setUid(uid);
        seller.setSname(user.getName());
        seller.setConsphone(user.getTelephone());

        ResultInfo info = new ResultInfo();

        boolean flag = sellerService.save(seller);
        //响应结果
        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("无法成为房东!");
        }

        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }


    public void beSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sidStr = request.getParameter("sid");
        int sid = Integer.parseInt(sidStr);
        boolean flag = sellerService.beSeller(sid);
        writeValue(flag,response);
    }

    public void notBeSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sidStr = request.getParameter("sid");
        int sid = Integer.parseInt(sidStr);
        boolean flag = sellerService.notBeSeller(sid);
        writeValue(flag,response);
    }

    public void wantBeSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

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

        PageBean<Seller> pb = sellerService.wantBeSellerList(currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);
    }

    public void canNotBeSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sidStr = request.getParameter("sid");
        int sid = Integer.parseInt(sidStr);
        boolean flag = sellerService.deleteSeller(sid);
        ResultInfo resultInfo = new ResultInfo();
        if(flag){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("没能成功驳回请求！");
        }
        writeValue(resultInfo,response);
    }



}
