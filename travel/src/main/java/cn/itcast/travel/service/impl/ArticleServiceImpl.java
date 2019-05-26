package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.ArticleDao;
import cn.itcast.travel.dao.impl.ArticleDaoImpl;
import cn.itcast.travel.domain.Article;
import cn.itcast.travel.service.ArticleService;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
