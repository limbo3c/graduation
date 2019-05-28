package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Seller;

public interface SellerService {

    boolean save(Seller seller);

    boolean beSeller(int sid);

    boolean notBeSeller(int sid);

    PageBean<Seller> wantBeSellerList(int currentPage,int pageSize);

    boolean deleteSeller(int sid);

}
