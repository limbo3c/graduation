package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

import java.util.List;

public interface UserDao {

    // 根据用户名查询用户信息
    User findByUsername(String username);

    //保存用户
    void save(User user);

    //根据激活码查用户
    User findByCode(String code);

    //更新激活状态
    void updateStatus(User user);

    //通过用户名密码查询
    User findByUsernameAndPassword(String username, String password);

    //通过id查用户
    User findByUid(int uid);

    //全部用户
    List<User> findAllUser(int start,int pageSize);

    //用户数
    int findUserCount();
}
