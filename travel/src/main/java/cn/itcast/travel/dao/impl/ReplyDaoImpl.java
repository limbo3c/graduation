package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.ReplyDao;
import cn.itcast.travel.domain.Reply;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ReplyDaoImpl implements ReplyDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<Reply> findByForWho(int forWho){
        String sql = "select * from tab_reply where forWho = ?";
        return template.query(sql,new BeanPropertyRowMapper<Reply>(Reply.class),forWho);
    }

    @Override
    public void save(Reply reply){
        String sql = "insert into tab_reply(uname,aid,forWho,content,createDate) values(?,?,?,?,?)";

        template.update(sql,reply.getUname(),
                reply.getAid(),
                reply.getForWho(),
                reply.getContent(),
                reply.getCreateDate()
        );
    }
}
