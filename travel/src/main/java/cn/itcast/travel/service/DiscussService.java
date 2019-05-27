package cn.itcast.travel.service;

import cn.itcast.travel.domain.Discuss;
import cn.itcast.travel.domain.PageBean;

public interface DiscussService {

    boolean saveDiscuss(Discuss discuss);

    boolean deleteDiscuss(int did);

    PageBean<Discuss> findDiscussByAid(int aid,int currentPage, int pageSize);

}
