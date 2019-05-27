package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Article;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.ArticleService;
import cn.itcast.travel.service.impl.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet {

    private ArticleService articleService = new ArticleServiceImpl();

    public void editArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String story = request.getParameter("story");
        String title = request.getParameter("title");
        User user = (User) request.getSession().getAttribute("user");
        int uid = 10;
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
            System.out.println(user.getUsername());
        }
        System.out.println(story);
        Article article = new Article();
        article.setTitle(title);
        article.setStory(story);
        article.setAuthor(uid);
        articleService.createArticle(article);
    }

    public void deleteArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aidStr = request.getParameter("aid");
        int aid = 0;
        if(aidStr != null && aidStr.length() > 0 && !"null".equals(aidStr)){
            aid = Integer.parseInt(aidStr);
        }
        boolean flag = articleService.deleteArticle(aid);
        ResultInfo info = new ResultInfo();
        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("删除失败!");
        }

        writeValue(info,response);

    }

    public void articleQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String aidStr = request.getParameter("aid");
        String title = request.getParameter("title");
        String story = request.getParameter("story");
        int aid = 0;
        if(aidStr != null && aidStr.length() > 0 && !"null".equals(aidStr)){
            aid = Integer.parseInt(aidStr);
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
            pageSize = 5;
        }

        PageBean<Article> pb = articleService.articleQuery(aid,currentPage,pageSize,title,story);

        //将pageBean对象序列化为json
        writeValue(pb,response);
    }

    public void hotArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Article> articles = articleService.hotArticle();
        writeValue(articles,response);
    }

    public void findOneArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aidStr = request.getParameter("aid");
        int aid = Integer.parseInt(aidStr);
        Article article = articleService.findOneArticle(aid);
        writeValue(article,response);
    }

}
