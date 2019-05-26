package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.User;

public interface UserService {

    boolean regist(User user);

    boolean active(String code);

    User login(User user);

    PageBean<User> allUserPageQuery( int currentPage, int pageSize);

    boolean seal(int uid);

    boolean unseal(int uid);
}
