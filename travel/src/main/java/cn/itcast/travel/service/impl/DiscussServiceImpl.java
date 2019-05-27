package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.DiscussDao;
import cn.itcast.travel.dao.ReplyDao;
import cn.itcast.travel.dao.impl.DiscussDaoImpl;
import cn.itcast.travel.dao.impl.ReplyDaoImpl;
import cn.itcast.travel.domain.Discuss;
import cn.itcast.travel.domain.House;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.service.DiscussService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DiscussServiceImpl implements DiscussService {
    private DiscussDao discussDao = new DiscussDaoImpl();

    private ReplyDao replyDao = new ReplyDaoImpl();

    @Override
    public boolean saveDiscuss(Discuss discuss){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        discuss.setCreateDate(df.format(new Date()));
        return true;
    }

    @Override
    public boolean deleteDiscuss(int did){
        Discuss discuss = discussDao.findOne(did);
        if(discuss != null){
            discussDao.delete(did);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public PageBean<Discuss> findDiscussByAid(int aid, int currentPage, int pageSize){
        PageBean<Discuss> pb = new PageBean<Discuss>();
        //当前页码
        pb.setCurrentPage(currentPage);
        //每页显示条数
        pb.setPageSize(pageSize);

        int totalCount = discussDao.findCountByAid(aid);
        pb.setTotalCount(totalCount);
        //当前页显示的数据集
        int start = (currentPage - 1) * pageSize;

        List<Discuss> list = discussDao.findByAid(aid,start,pageSize);

        for (int i=0;i<list.size();i++){
            Discuss discuss = list.get(i);
            System.out.println(replyDao.findByForWho(discuss.getDid()));
            discuss.setReplyList(replyDao.findByForWho(discuss.getDid()));
            list.set(i,discuss);
        }
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;
    }

}
