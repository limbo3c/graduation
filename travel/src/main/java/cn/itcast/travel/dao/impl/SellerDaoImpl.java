package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SellerDaoImpl implements SellerDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void save(Seller seller){
        String sql = "insert into tab_seller(uid,sname,consphone,address,identity,isSeller,createDate,updateDate,zfCode,wxCode) values(?,?,?,?,?,?,?,?,?,?)";


        template.update(sql,seller.getUid(),
                seller.getSname(),
                seller.getConsphone(),
                seller.getAddress(),
                seller.getIdentity(),
                seller.getIsSeller(),
                seller.getCreateDate(),
                seller.getUpdateDate(),
                seller.getZfCode(),
                seller.getWxCode()
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

    @Override
    public void beSeller(int sid){
        String sql = "update tab_seller set isSeller = 'Y' where sid = ?";
        template.update(sql,sid);
        changeUpdateDate(sid);
    }


    @Override
    public void notBeSeller(int sid){
        String sql = "update tab_seller set isSeller = 'N' where sid = ?";
        template.update(sql,sid);
        changeUpdateDate(sid);
    }

    @Override
    public List<Seller> wantBeSeller(int start,int pageSize ){
        String sql = "select * from tab_seller where isSeller = 'N' limit ?,?";
        return template.query(sql,new BeanPropertyRowMapper<Seller>(Seller.class),start,pageSize);
    }

    @Override
    public int wantBeSellerCount(){
        String sql = "select * from tab_seller where isSeller = 'N'";
        return template.query(sql,new BeanPropertyRowMapper<Seller>(Seller.class)).size();
    }

    @Override
    public void changeUpdateDate(int sid){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql = "update tab_seller set updateDate = ? where sid = ?";
        template.update(sql,df.format(new Date()),sid);
    }

    @Override
    public void delete(int sid){
        String sql = "delete from tab_seller where sid = ?";
        template.update(sql,sid);
    }

    @Override
    public Seller findOne(int sid){
        String sql = "select * from tab_seller where sid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }
}
