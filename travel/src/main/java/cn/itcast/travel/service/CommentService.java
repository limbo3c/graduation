package cn.itcast.travel.service;

import cn.itcast.travel.domain.Comment;
import cn.itcast.travel.domain.PageBean;

import java.util.List;

public interface CommentService {

    void saveComment(Comment comment);

    boolean deleteComment(int cmid);

    PageBean<Comment> findByUid(int uid, int currentPage, int pageSize);

    PageBean<Comment> findByHid(int hid, int currentPage, int pageSize);

    PageBean<Comment> allCommentPageQuery(int hid, int currentPage, int pageSize, String uname);

}
