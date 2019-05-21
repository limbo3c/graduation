package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CommentDao;
import cn.itcast.travel.domain.Comment;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class CommentDaoImpl implements CommentDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public void save(Comment comment){
        String sql = "insert into tab_comment(cmid,content,hid,uid,createTime,uname) values(?,?,?,?,?,?)";
        template.update(sql,comment.getCmid(),
                comment.getContent(),
                comment.getHid(),
                comment.getUid(),
                comment.getCreateTime(),
                comment.getUname()
        );
    }


    @Override
    public void deleteComment(int cmid){
        String sql = "delete from tab_comment where cmid = ?";
        Object args[] = new Object[]{cmid};
        template.update(sql,args);
    }


    @Override
    public List<Comment> findByUid(int uid ,int start, int pageSize){
//        String sql = "select * from tab_comment where uid = ? ";
//        return template.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class),uid);


        String sql = " select * from tab_comment where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(uid != 0){
            sb.append( " and uid = ? ");

            params.add(uid);
        }


        sb.append(" limit  ?,? ");

        sql = sb.toString();

        params.add(pageSize);
        params.add(start);



        return template.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class),params.toArray());


    }

    @Override
    public List<Comment> findByHid(int hid,int start,int pageSize){
//        String sql = "select * from tab_comment where hid = ? ";
//        return template.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class),hid);


        String sql = " select * from tab_comment where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(hid != 0){
            sb.append( " and hid = ? ");

            params.add(hid);
        }


        sb.append(" limit  ?,? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);


        return template.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class),params.toArray());
    }


    @Override
    public int findTotalCountByHid(int hid){
        String sql = "select count(*) from tab_comment where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(hid != 0){
            sb.append( " and hid = ? ");

            params.add(hid);//添加？对应的值
        }


        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Comment> findCommentByPage(int hid , int start , int pageSize, String uname){


        String sql = " select * from tab_comment where 1 = 1 ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(hid != 0){
            sb.append( " and hid = ? ");

            params.add(hid);
        }

        if(uname != null && uname.length() > 0){
            sb.append(" and uname like ? ");

            params.add("%"+uname+"%");
        }
        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);
        return template.query(sql,new BeanPropertyRowMapper<Comment>(Comment.class),params.toArray());
    }

    @Override
    public int findHouseCount(int hid,String uname){
        String sql = "select count(*) from tab_comment where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(hid != 0){
            sb.append( " and hid = ? ");

            params.add(hid);//添加？对应的值
        }

        if(uname != null && uname.length() > 0){
            sb.append(" and uname like ? ");

            params.add("%"+uname+"%");
        }

        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public  Comment findOneComment(int cmid){
        String sql = "select * from tab_comment where cmid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Comment>(Comment.class),cmid);
    }
}
