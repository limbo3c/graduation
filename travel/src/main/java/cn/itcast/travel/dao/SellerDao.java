package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

import java.util.List;

public interface SellerDao {
    //根据id查询
    public Seller findById(int sid);

    void save(Seller seller);

    Seller findByUid(int uid);

    void beSeller(int sid);

    void notBeSeller(int sid);

    List<Seller> wantBeSeller(int start,int pageSize );

    void changeUpdateDate(int sid);

    int wantBeSellerCount();

    void delete(int sid);

    Seller findOne(int sid);

}
