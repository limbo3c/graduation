package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CommentDao;
import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.CommentDaoImpl;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Comment;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.service.CommentService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentDao commentDao = new CommentDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void saveComment(Comment comment){
        int uid = comment.getUid();
        comment.setUname(userDao.findByUid(uid).getName());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setCreateTime(df.format(new Date()));
        commentDao.save(comment);
    }

    @Override
    public boolean deleteComment(int cmid){
        Comment comment = commentDao.findOneComment(cmid);
        if(comment != null){
            commentDao.deleteComment(cmid);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public PageBean<Comment> findByUid(int uid, int currentPage, int pageSize){

        PageBean<Comment> pb = new PageBean<Comment>();
        //当前页码
        pb.setCurrentPage(currentPage);
        //每页显示条数
        pb.setPageSize(pageSize);

        //当前页显示的数据集
        int start = (currentPage - 1) * pageSize;
        List<Comment> list = commentDao.findByUid(uid,start,pageSize);
        pb.setList(list);
        int totalCount = list.size();
        System.out.println(totalCount);
        pb.setTotalCount(totalCount);
        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;

    }

    @Override
    public PageBean<Comment> findByHid(int hid, int currentPage, int pageSize){
        PageBean<Comment> pb = new PageBean<Comment>();
        //当前页码
        pb.setCurrentPage(currentPage);
        //每页显示条数
        pb.setPageSize(pageSize);

        int totalCount = commentDao.findTotalCountByHid(hid);

        pb.setTotalCount(totalCount);

        //当前页显示的数据集
        int start = (currentPage - 1) * pageSize;
        List<Comment> list = commentDao.findByHid(hid,start,pageSize);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;

    }

    @Override
    public PageBean<Comment> allCommentPageQuery(int hid, int currentPage, int pageSize, String uname){

        PageBean<Comment> pb = new PageBean<Comment>();
        //当前页码
        pb.setCurrentPage(currentPage);
        //每页显示条数
        pb.setPageSize(pageSize);

        //总记录数
        int totalCount = commentDao.findCommentCount(hid,uname);
        pb.setTotalCount(totalCount);
        //当前页显示的数据集
        int start = (currentPage - 1) * pageSize;
        List<Comment> list = commentDao.findCommentByPage(hid,start,pageSize,uname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;
    }

}
