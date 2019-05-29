package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Comment;

import java.util.List;

public interface CommentDao {
    void save(Comment comment);

    void deleteComment(int cmid);

    int findTotalCountByHid(int hid);

    List<Comment> findByUid(int uid,int start,int pageSize);

    List<Comment> findByHid(int hid,int start,int pageSize);

    List<Comment> findCommentByPage(int hid , int start , int pageSize, String uname);

    int findCommentCount(int hid,String uname);

    Comment findOneComment(int cmid);
}
