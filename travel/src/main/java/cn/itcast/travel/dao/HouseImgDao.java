package cn.itcast.travel.dao;

import cn.itcast.travel.domain.HouseImg;

import java.util.List;

public interface HouseImgDao {

    //根据房源的id查询图片

    List<HouseImg> findByHid(int hid);

    void save(HouseImg houseImg);
}
