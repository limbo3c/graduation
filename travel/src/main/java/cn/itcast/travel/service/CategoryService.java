package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> searchAll();

    Boolean createCategory(String cname);

    Boolean isExitCategory(String cname);
}
