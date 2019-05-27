package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Discuss;

import java.util.List;

public interface DiscussDao {

    void save(Discuss discuss);

    void delete(int did);

    List<Discuss> findByAid(int aid,int start,int pageSize);

    int findCountByAid(int aid);

    Discuss findOne(int did);


}
