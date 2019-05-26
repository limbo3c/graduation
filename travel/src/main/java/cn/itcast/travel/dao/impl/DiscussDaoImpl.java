package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.DiscussDao;
import cn.itcast.travel.domain.Discuss;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class DiscussDaoImpl implements DiscussDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
}
