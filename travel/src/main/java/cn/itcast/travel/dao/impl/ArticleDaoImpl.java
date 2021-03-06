package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.ArticleDao;
import cn.itcast.travel.domain.Article;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class ArticleDaoImpl implements ArticleDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void save(Article article){
        String sql = "insert into tab_article(author,title,story,createDate,fabulous,authorName,updateDate) values(?,?,?,?,?,?,?)";

        template.update(sql,article.getAuthor(),
                article.getTitle(),
                article.getStory(),
                article.getCreateDate(),
                article.getFabulous(),
                article.getAuthorName(),
                article.getUpdateDate()
        );
    }

    @Override
    public void delete(int aid){
        String sql = "delete from tab_article where aid = ?";
        template.update(sql,aid);
    }

    @Override
    public List<Article> findArticle(int aid , int start , int pageSize, String title, String story){

        String sql = " select * from tab_article where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(aid != 0){
            sb.append( " and aid = ? ");

            params.add(aid);
        }

        if(title != null && title.length() > 0){
            sb.append(" and title like ? ");

            params.add("%"+title+"%");
        }

        if(story != null && story.length() > 0){
            sb.append(" and story like ? ");

            params.add("%"+story+"%");
        }
        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Article>(Article.class),params.toArray());
    }

    @Override
    public int findArticleCount(int aid , String title,String story){
        String sql = " select count(*) from tab_article where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(aid != 0){
            sb.append( " and aid = ? ");

            params.add(aid);
        }

        if(title != null && title.length() > 0){
            sb.append(" and title like ? ");

            params.add("%"+title+"%");
        }

        if(story != null && story.length() > 0){
            sb.append(" and story like ? ");

            params.add("%"+story+"%");
        }

        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public Article findOne(int aid){
        String sql = "select * from tab_article where aid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Article>(Article.class),aid);
    }

    @Override
    public List<Article> hotArticle(){
        String sql = "select * from tab_article order by fabulous desc limit 5";
        return template.query(sql,new BeanPropertyRowMapper<Article>(Article.class));
    }

    @Override
    public void increaseFabulous(int aid){
        String sql = "update tab_article set fabulous = fabulous + 1 where aid = ?";

        template.update(sql,aid);
    }

    @Override
    public void decreaseFabulous(int aid){
        String sql = "update tab_article set fabulous = fabulous - 1 where aid = ?";

        template.update(sql,aid);
    }

    @Override
    public List<Article> findByAuthor(int author,int start,int pageSize){

        String sql = " select * from tab_article where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(author != 0){
            sb.append( " and author = ? ");

            params.add(author);
        }

        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Article>(Article.class),params.toArray());
    }

    @Override
    public int findCountByAuthor(int author){
        String sql = " select count(*) from tab_article where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(author != 0){
            sb.append( " and author = ? ");

            params.add(author);
        }


        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Article> findByAuthorNameAndTitle(String authorName,String title,int start,int pageSize){

        String sql = " select * from tab_article where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();
        if(authorName != null && authorName.length() > 0){
            sb.append(" and authorName like ? ");

            params.add("%"+authorName+"%");
        }

        if(title != null && title.length() > 0){
            sb.append(" and title like ? ");

            params.add("%"+title+"%");
        }


        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Article>(Article.class),params.toArray());
    }

    @Override
    public int findCountByAuthorNameAndTitle(String authorName,String title){
        String sql = " select count(*) from tab_article where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(authorName != null && authorName.length() > 0){
            sb.append(" and authorName like ? ");

            params.add("%"+authorName+"%");
        }

        if(title != null && title.length() > 0){
            sb.append(" and title like ? ");

            params.add("%"+title+"%");
        }
        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

   @Override
    public void update(Article article){
        String sql = "update tab_article set title = ? , story = ? , aimage = ? ,updateDate = ? , fabulous = ? where aid = ?";

        template.update(sql,article.getTitle(),
                article.getStory(),
                article.getAimage(),
                article.getUpdateDate(),
                article.getFabulous(),
                article.getAid());
   }
}
