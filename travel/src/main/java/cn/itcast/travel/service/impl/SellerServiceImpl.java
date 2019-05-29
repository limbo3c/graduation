package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.SellerService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SellerServiceImpl implements SellerService {
    private SellerDao sellerDao = new SellerDaoImpl();

    @Override
    public boolean save(Seller seller){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (seller!=null){
            seller.setIsSeller("N");
            seller.setCreateDate(df.format(new Date()));
            seller.setUpdateDate(df.format(new Date()));
            sellerDao.save(seller);
            return true;
        }
        return false;

    }

    @Override
    public boolean beSeller(int sid){
        Seller seller = sellerDao.findById(sid);
        if (seller!=null&&seller.getIsSeller().equals("N")){
            sellerDao.beSeller(sid);
            return true;
        }else {
            return false;
        }

    }


    @Override
    public boolean notBeSeller(int sid){
        Seller seller = sellerDao.findById(sid);
        if (seller!=null&&seller.getIsSeller().equals("Y")){
            sellerDao.notBeSeller(sid);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public PageBean<Seller> wantBeSellerList(int currentPage, int pageSize){
        PageBean<Seller> pb = new PageBean<Seller>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = sellerDao.wantBeSellerCount();

        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Seller> list = sellerDao.wantBeSeller(start,pageSize);
        pb.setList(list);

        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public boolean deleteSeller(int sid){
        Seller seller = sellerDao.findById(sid);
        if (seller !=null){
            sellerDao.delete(sid);
            return true;
        }
        return false;
    }

    @Override
    public Seller findSellerByUid(int uid){
        Seller seller = sellerDao.findByUid(uid);

        return seller;
    }

    @Override
    public boolean existSeller(int uid){
        Seller seller = sellerDao.findByUid(uid);
        if (seller==null){
            return false;
        }
        return true;
    }
}
