package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {

            String sql = "select * from tab_user where username = ?";

            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (Exception e) {

        }

        return user;
    }

    @Override
    public void save(User user) {

        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";


        template.update(sql,user.getUsername(),
                    user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
                );
    }

    //根据激活码查询用户对象

    @Override
    public User findByCode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";

            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        return user;
    }

     //修改用户激活状态

    @Override
    public void updateStatus(User user) {
        String sql = " update tab_user set status = 'Y' where uid=?";
        template.update(sql,user.getUid());
    }

    //根据用户名和密码查询的方法

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        try {
            //1.定义sql
            String sql = "select * from tab_user where username = ? and password = ?";
            //2.执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username,password);
        } catch (Exception e) {

        }

        return user;
    }

    @Override
    public User findByUid(int uid){
        User user = null;
        try {

            String sql = "select * from tab_user where uid = ?";

            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uid);
        } catch (Exception e) {

        }

        return user;
    }



    @Override
    public List<User> findAllUser(int start,int pageSize){
        String sql = " select * from tab_user ";

        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        sb.append(" limit ? , ? ");

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);

        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());


//        String sql = "select * from tab_user";
//        return template.query(sql,new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public int findUserCount(){
        String sql = "select * from tab_user";
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class)).size();
    }


    @Override
    public void seal(int uid){
        String sql = "update tab_user set status = 'N' where uid = ?";
        template.update(sql,uid);
    }

    @Override
    public void unseal(int uid){
        String sql = "update tab_user set status = 'Y' where uid = ?";
        template.update(sql,uid);
    }

    @Override
    public void updateUser(User user){
        String sql = "update tab_user set username = ? , password = ?, name = ? , birthday = ? , sex = ? , telephone = ? , email = ? where uid = ?";
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getUid()
                );
    }

}
