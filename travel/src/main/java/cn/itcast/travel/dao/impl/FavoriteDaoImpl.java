package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public Favorite findByHidAndUid(int hid, int uid) {
        Favorite favorite = null;
        try {
            String sql = " select * from tab_favorite where hid = ? and uid = ?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), hid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;
    }

    @Override
    public int findCountByHid(int hid) {
        String sql = "SELECT COUNT(*) FROM tab_favorite WHERE hid = ?";

        return template.queryForObject(sql,Integer.class,hid);
    }

    @Override
    public void add(int hid, int uid) {
        String sql = "insert into tab_favorite values(?,?,?)";

        template.update(sql,hid,new Date(),uid);
    }

    @Override
    public List<Favorite> findByUid(int uid){

        String sql = "select * from tab_favorite where uid = ?";
        return template.query(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),uid);
    }


}
