package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.ArticleDao;
import cn.itcast.travel.dao.impl.ArticleDaoImpl;
import cn.itcast.travel.domain.Article;
import cn.itcast.travel.domain.ArticleFavorite;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.service.ArticleService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    private ArticleDao articleDao = new ArticleDaoImpl();

    @Override
    public boolean createArticle(Article article){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setFabulous(0);
        article.setCreateDate(df.format(new Date()));
        articleDao.save(article);
        return true;
    }

    @Override
    public boolean deleteArticle(int aid){
        Article article = articleDao.findOne(aid);
        if (article==null){
            return false;
        }
        articleDao.delete(aid);
        return true;
    }

    @Override
    public PageBean<Article> articleQuery(int aid, int currentPage, int pageSize, String title, String story){
        PageBean<Article> pb = new PageBean<Article>();
        //当前页码
        pb.setCurrentPage(currentPage);
        //每页显示条数
        pb.setPageSize(pageSize);

        //总记录数
        int totalCount = articleDao.findArticleCount(aid,title,story);
        pb.setTotalCount(totalCount);
        //当前页显示的数据集
        int start = (currentPage - 1) * pageSize;
        List<Article> list = articleDao.findArticle(aid,start,pageSize,title,story);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;
    }


    @Override
    public List<Article> hotArticle(){
        return articleDao.hotArticle();
    }

    @Override
    public Article findOneArticle(int aid){
        return articleDao.findOne(aid);

    }

    @Override
    public void increaseFabulous(int aid){
        articleDao.increaseFabulous(aid);
    }

    @Override
    public void decreaseFabulous(int aid){
        articleDao.decreaseFabulous(aid);
    }

    @Override
    public PageBean<Article> myArticle(int author,int currentPage,int pageSize){
        PageBean<Article> pb = new PageBean<Article>();
        //当前页码
        pb.setCurrentPage(currentPage);
        //每页显示条数
        pb.setPageSize(pageSize);

        //总记录数
        int totalCount = articleDao.findCountByAuthor(author);
        pb.setTotalCount(totalCount);
        //当前页显示的数据集
        int start = (currentPage - 1) * pageSize;
        List<Article> list = articleDao.findByAuthor(author,start,pageSize);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;
    }

    @Override
    public  PageBean<Article> myFavoriteArticle(List<ArticleFavorite> articleFavorites, int currentPage, int pageSize){
        PageBean<Article> pb = new PageBean<Article>();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = articleFavorites.size();
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Article> articles = new ArrayList<>();

        Article article;
        int aid;
        for (int i=0;i<articleFavorites.size();i++){
            aid = articleFavorites.get(i).getAid();
            article = articleDao.findOne(aid);
            articles.add(article);
        }
        pb.setList(articles);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }
}
