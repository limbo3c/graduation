package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.SellerService;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.SellerServiceImpl;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*") // /user/add /user/find
public class UserServlet extends BaseServlet {

    //声明UserService业务对象
    private UserService service = new UserServiceImpl();

    private SellerService sellerService = new SellerServiceImpl();

    //注册功能

    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次

        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //获取数据
        Map<String, String[]> map = request.getParameterMap();

        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用service完成注册
        //UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //响应结果
        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }

        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    //登录功能

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();

        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


       // UserService service = new UserServiceImpl();
        User u  = service.login(user);

        ResultInfo info = new ResultInfo();


        if(u == null){
            //用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名密码或错误");
        }
        //判断用户是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }
        //判断登录成功
        if(u != null && "Y".equals(u.getStatus())){
            request.getSession().setAttribute("user",u);

            //登录成功
            info.setFlag(true);
        }


        writeValue(info,response);
    }

    // 查询单个对象

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端

        writeValue(user,response);
    }

    //退出功能

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();

        //2.跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    //激活功能

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取激活码
        String code = request.getParameter("code");
        if(code != null){

            //UserService service = new UserServiceImpl();
            boolean flag = service.active(code);

            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else{
                //激活失败
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }


    public void allUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

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

        PageBean<User> pb = service.allUserPageQuery(currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);
    }


    public void seal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uidStr = request.getParameter("uid");
        int uid = Integer.parseInt(uidStr);
        boolean flag = service.seal(uid);
        ResultInfo info = new ResultInfo();

        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("封禁失败!");
        }

        writeValue(info,response);

    }



    public void unseal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uidStr = request.getParameter("uid");
        int uid = Integer.parseInt(uidStr);
        boolean flag = service.unseal(uid);
        ResultInfo info = new ResultInfo();

        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("解封失败!");
        }

        writeValue(info,response);
    }


    public void updateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.toString());
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("/////////////");
        System.out.println(user.toString());

        boolean flag = service.updateUser(user);
        ResultInfo info = new ResultInfo();
        //响应结果
        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("修改失败!");
        }

        writeValue(info,response);
    }


    public void sellerFlag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uidStr = request.getParameter("uid");
        int uid = Integer.parseInt(uidStr);
        boolean flag=true;
        if (sellerService.existSeller(uid)){
            Seller seller = sellerService.findSellerByUid(uid);
            if (seller.getIsSeller().equals("Y")){
                flag=false;
            }
        }

        System.out.println(flag);
        writeValue(flag,response);

    }
}
