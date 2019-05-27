package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Reply;

import java.util.List;

public interface ReplyDao {

    List<Reply> findByForWho(int forWho);

    void save(Reply reply);

}
