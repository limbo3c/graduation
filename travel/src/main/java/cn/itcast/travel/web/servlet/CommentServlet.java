package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Comment;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.service.CommentService;
import cn.itcast.travel.service.impl.CommentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comment/*")
public class CommentServlet extends BaseServlet {

    private CommentService commentService = new CommentServiceImpl();

    public void userComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pageSizeStr = request.getParameter("pageSize");
        String currentPageStr = request.getParameter("currentPage");

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


        PageBean<Comment> pb = commentService.findByUid(uid,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);

    }

    public void houseComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String hidStr = request.getParameter("hid");

        int hid = 0;//类别id

        if(hidStr != null && hidStr.length() > 0 && !"null".equals(hidStr)){
            hid = Integer.parseInt(hidStr);
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


        PageBean<Comment> pb = commentService.findByHid(hid,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);

    }


    public void allComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String hidStr = request.getParameter("hid");

        String uname = request.getParameter("uname");
        if(uname!=null){
            uname = new String(uname.getBytes("iso-8859-1"),"utf-8");
        }


        int hid = 0;//类别id

        if(hidStr != null && hidStr.length() > 0 && !"null".equals(hidStr)){
            hid = Integer.parseInt(hidStr);
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


        PageBean<Comment> pb = commentService.allCommentPageQuery(hid, currentPage, pageSize,uname);

        //将pageBean对象序列化为json
        writeValue(pb,response);
    }

    public void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cmidStr = request.getParameter("cmid");
        int cmid = 0;
        if(cmidStr != null && cmidStr.length() > 0 && !"null".equals(cmidStr)){
            cmid = Integer.parseInt(cmidStr);
        }

        boolean flag = commentService.deleteComment(cmid);
        ResultInfo info = new ResultInfo();
        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("删除失败!");
        }


        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

}
