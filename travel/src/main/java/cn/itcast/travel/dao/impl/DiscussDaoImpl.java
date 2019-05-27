package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.DiscussDao;
import cn.itcast.travel.domain.Discuss;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class DiscussDaoImpl implements DiscussDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void save(Discuss discuss){
        String sql = "insert into tab_discuss(uid,uname,aid,content,createDate) values(?,?,?,?,?)";
        template.update(sql,discuss.getUid(),
                discuss.getUname(),
                discuss.getAid(),
                discuss.getContent(),
                discuss.getCreateDate()
        );
    }

    @Override
    public void delete(int did){
        String sql = "delete from tab_discuss where did = ?";
        template.update(sql,did);

    }

    @Override
    public List<Discuss> findByAid(int aid, int start, int pageSize){

        String sql = " select * from tab_discuss where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(aid != 0){
            sb.append( " and aid = ? ");

            params.add(aid);
        }

        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql,new BeanPropertyRowMapper<Discuss>(Discuss.class),params.toArray());

    }

    @Override
    public int findCountByAid(int aid){
        String sql = "select count(*) from tab_discuss where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(aid != 0){
            sb.append( " and aid = ? ");

            params.add(aid);//添加？对应的值
        }


        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public Discuss findOne(int did){
        String sql = "select * from tab_discuss where did = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Discuss>(Discuss.class),did);
    }

}
