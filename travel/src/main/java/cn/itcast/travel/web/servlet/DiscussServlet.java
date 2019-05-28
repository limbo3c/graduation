package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Discuss;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Reply;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.DiscussService;
import cn.itcast.travel.service.impl.DiscussServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/discuss/*")
public class DiscussServlet extends BaseServlet {

    private DiscussService discussService = new DiscussServiceImpl();

    public void saveDiscuss(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aidStr = request.getParameter("aid");
        int aid = Integer.parseInt(aidStr);
        User user = (User) request.getSession().getAttribute("user");
        int uid ;
        String uname;
        if(user == null){
            uid = 10;
            uname = "limbo32c";
        }else{
            //用户已经登录
            uid = user.getUid();
            uname = user.getName();
        }
        String content = request.getParameter("content");
        Discuss discuss = new Discuss();
        discuss.setUid(uid);
        discuss.setAid(aid);
        discuss.setContent(content);
        discuss.setUname(uname);
        discussService.saveDiscuss(discuss);
        writeValue(true,response);
    }

    public void discussQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String aidStr = request.getParameter("aid");
        int aid = Integer.parseInt(aidStr);
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


        PageBean<Discuss> pb = discussService.findDiscussByAid(aid,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);

    }


    public void saveReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aidStr = request.getParameter("aid");
        int aid = Integer.parseInt(aidStr);
        User user = (User) request.getSession().getAttribute("user");
        String uname;
        if(user == null){
            uname = "limbo32c";
        }else{
            uname = user.getName();
        }
        String content = request.getParameter("content");
        String forWhoStr = request.getParameter("forWho");
        int forWho = Integer.parseInt(forWhoStr);
        Reply reply = new Reply();
        reply.setAid(aid);
        reply.setUname(uname);
        reply.setContent(content);
        reply.setForWho(forWho);
        System.out.println(content);
        discussService.saveReply(reply);


    }

}
