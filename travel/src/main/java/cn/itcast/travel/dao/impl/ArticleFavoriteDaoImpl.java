package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.ArticleFavoriteDao;
import cn.itcast.travel.domain.ArticleFavorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class ArticleFavoriteDaoImpl implements ArticleFavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public ArticleFavorite findByAidAndUid(int aid, int uid){
        ArticleFavorite articleFavorite = null;
        try {
            String sql = " select * from tab_article_favorite where aid = ? and uid = ?";
            articleFavorite = template.queryForObject(sql, new BeanPropertyRowMapper<ArticleFavorite>(ArticleFavorite.class), aid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return articleFavorite;
    }

    @Override
    public void add(int aid, int uid){
        String sql = "insert into tab_article_favorite values(?,?,?)";

        template.update(sql,aid,new Date(),uid);
    }

    @Override
    public List<ArticleFavorite> findByUid(int uid){

        String sql = "select * from tab_article_favorite where uid = ?";
        return template.query(sql,new BeanPropertyRowMapper<ArticleFavorite>(ArticleFavorite.class),uid);
    }
}
