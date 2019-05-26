package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Article;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.ArticleService;
import cn.itcast.travel.service.impl.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet {

    private ArticleService articleService = new ArticleServiceImpl();

    public void editArticle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String story = request.getParameter("article");
        String title = request.getParameter("title");
        User user = (User) request.getSession().getAttribute("user");
        int uid = 10;
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }
        System.out.println(story);
        Article article = new Article();
        article.setTitle(title);
        article.setStory(story);
        article.setAuthor(uid);
        articleService.createArticle(article);
    }


}
