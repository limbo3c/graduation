package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.HouseImgDao;
import cn.itcast.travel.domain.HouseImg;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class HouseImgDaoImpl implements HouseImgDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<HouseImg> findByHid(int hid) {
        String sql = "select * from tab_house_img where hid = ? ";
        return template.query(sql,new BeanPropertyRowMapper<HouseImg>(HouseImg.class),hid);
    }

    @Override
    public void save(HouseImg houseImg){
        String sql = "insert into tab_house_img(hid,bigPic,smallPic) values(?,?,?)";

        template.update(sql,houseImg.getHid(),
                houseImg.getBigPic(),
                houseImg.getSmallPic()
        );
    }

}
