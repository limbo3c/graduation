package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.SellerService;

public class SellerServiceImpl implements SellerService {
    private SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public boolean save(Seller seller){
        if (seller!=null){
            sellerDao.save(seller);
            return true;
        }
        return false;

    }
}
