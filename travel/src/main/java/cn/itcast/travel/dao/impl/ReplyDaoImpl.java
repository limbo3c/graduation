package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.ReplyDao;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class ReplyDaoImpl implements ReplyDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
}
