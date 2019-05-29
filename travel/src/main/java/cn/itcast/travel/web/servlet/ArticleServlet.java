package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.ArticleService;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.impl.ArticleServiceImpl;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;

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

    private FavoriteService favoriteService = new FavoriteServiceImpl();

    public void editArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String story = request.getParameter("story");
        String title = request.getParameter("title");
        User user = (User) request.getSession().getAttribute("user");
        int uid = 10;
        String uname = "limbo32c";
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
            uname = user.getUsername();

        }
        Article article = new Article();
        article.setTitle(title);
        article.setStory(story);
        article.setAuthor(uid);
        article.setAuthorName(uname);
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

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String aid = request.getParameter("aid");

        //获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if(user == null){
            //用户尚未登录
            uid = 0;
        }else{
            //用户已经登录
            uid = user.getUid();
        }


        boolean flag = favoriteService.isArticleFavorite(aid,uid);

        //写回客户端
        writeValue(flag,response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aid = request.getParameter("aid");
        //当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }

        favoriteService.addArticleFavorite(aid,uid);
    }

    public void dianZan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String aid = request.getParameter("aid");
        articleService.increaseFabulous(Integer.parseInt(aid));
    }

    public void myArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }
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
            pageSize = 16;
        }

        PageBean<Article> pb = articleService.myArticle(uid,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);

    }

    public void myFavoriteArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }
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
            pageSize = 16;
        }
        List<ArticleFavorite> articleFavorites = favoriteService.myArticleFavorite(uid);

        PageBean<Article> pb = articleService.myFavoriteArticle(articleFavorites,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);
    }

    public void articleManageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorName = request.getParameter("authorName");
        String title = request.getParameter("title");
        if(title!=null){
            title = new String(title.getBytes("iso-8859-1"),"utf-8");
        }
        if(authorName!=null){
            authorName = new String(authorName.getBytes("iso-8859-1"),"utf-8");
        }
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

        PageBean<Article> pb = articleService.articleManageQuery(authorName,title,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);

    }
    public void updateFabulous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fabulousStr = request.getParameter("fabulous");
        String aidStr = request.getParameter("aid");
        int aid = Integer.parseInt(aidStr);
        int fabulous = Integer.parseInt(fabulousStr);

        boolean flag = articleService.updateFabulous(aid,fabulous);
        ResultInfo resultInfo =  new ResultInfo();
        if (flag){
            resultInfo.setFlag(true);
        }else {
            resultInfo.setErrorMsg("修改失败");
            resultInfo.setFlag(false);
        }
        writeValue(resultInfo,response);

    }


}
