package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    //注册用户
    @Override
    public boolean regist(User user) {

        User u = userDao.findByUsername(user.getUsername());

        if(u != null){

            return false;
        }

        //设置激活码
        user.setCode(UuidUtil.getUuid());
        //设置激活状态
        user.setStatus("N");
        userDao.save(user);

        //发邮件

        String content="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }

    // 激活用户

    @Override
    public boolean active(String code) {
        //1.根据激活码查询用户对象
        User user = userDao.findByCode(code);
        if(user != null){
            //2.调用dao的修改激活状态的方法
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }



    }

    /// 登录方法

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }


    @Override
    public PageBean<User> allUserPageQuery(int currentPage, int pageSize){
        PageBean<User> pb = new PageBean<User>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = userDao.findUserCount();
        pb.setTotalCount(totalCount);
        System.out.println("total:"+totalCount);
        int start = (currentPage - 1) * pageSize;
        List<User> users = userDao.findAllUser(start,pageSize);
        pb.setList(users);
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }


    @Override
    public boolean seal(int uid){
        User user = userDao.findByUid(uid);
        if(user != null){
            //2.调用dao的修改激活状态的方法
            userDao.seal(uid);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean unseal(int uid){
        User user = userDao.findByUid(uid);
        if(user != null){
            //2.调用dao的修改激活状态的方法
            userDao.unseal(uid);
            return true;
        }else{
            return false;
        }
    }

}
