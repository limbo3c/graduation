package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void save(Seller seller){
        String sql = "insert into tab_seller(uid,sname,consphone,address,identity) values(?,?,?,?,?)";


        template.update(sql,seller.getUid(),
                seller.getSname(),
                seller.getConsphone(),
                seller.getAddress(),
                seller.getIdentity()
        );
    }


    @Override
    public Seller findById(int sid) {

        String sql = "select * from tab_seller where sid = ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }

    @Override
    public Seller findByUid(int uid) {

        String sql = "select * from tab_seller where uid = ? ";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),uid);
    }
}
