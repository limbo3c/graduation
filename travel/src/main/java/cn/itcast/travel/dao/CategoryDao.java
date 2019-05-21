package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryDao {
    //查所有
    List<Category> findAll();

    Category findByCname(String cname);

    void save(String cname);

}
