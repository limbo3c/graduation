package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.*;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.HouseService;
import cn.itcast.travel.service.SellerService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.HouseServiceImpl;
import cn.itcast.travel.service.impl.SellerServiceImpl;
import cn.itcast.travel.util.LayuiUtil;
import cn.itcast.travel.util.CopyUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



@WebServlet("/house/*")
public class HouseServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();
    private HouseService houseService = new HouseServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    private SellerService sellerService = new SellerServiceImpl();

    //分页查询

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        String hname = request.getParameter("hname");
        if(hname!=null){
            hname = new String(hname.getBytes("iso-8859-1"),"utf-8");
        }


        int cid = 0;//类别id

        if(cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }


        PageBean<House> pb = houseService.pageQuery(cid, currentPage, pageSize,hname);

        //将pageBean对象序列化为json
        writeValue(pb,response);

    }

    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String uidStr = request.getParameter("uid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();//类别id

        if(uidStr != null && uidStr.length() > 0 && !"null".equals(uidStr)){
            uid = Integer.parseInt(uidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 16;
        }

        List<Favorite> favorites = favoriteService.myFavorite(uid);
        System.out.println(favorites);
        PageBean<House> pb = houseService.favoritePageQuery(favorites,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);

    }

    //根据id查询详细信息

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String hid = request.getParameter("hid");

        House house = houseService.findOne(hid);

        writeValue(house,response);
    }

    //判断当前登录用户是否收藏过该线路

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String hid = request.getParameter("hid");

        //获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if(user == null){
            //用户尚未登录
            uid = 0;
        }else{
            //用户已经登录
            uid = user.getUid();
        }
        System.out.println("hid:"+hid);
        System.out.println("uid:"+uid);
        //调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(hid, uid);

        //写回客户端
        writeValue(flag,response);
    }

    //添加收藏

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String hid = request.getParameter("hid");
        //当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }

        favoriteService.add(hid,uid);

    }

    public void houseLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        FileItemFactory fileItemFactory = new DiskFileItemFactory();
//        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
//        List items = null;
//        try {
//            items = servletFileUpload.parseRequest(request);
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }
//
//        Iterator iter = items.iterator();
//        while(iter.hasNext()){
//            FileItem item = (FileItem)iter.next();
//            if (item.isFormField()){
//
//            }else{
//                String fieldName = item.getFieldName();
//                System.out.println("fieldName:" + fieldName);
//                String filename = item.getName();
//                String t1 = System.getProperty("user.dir").substring(0,
//                        System.getProperty("user.dir").length() - 4);
//                System.out.println("t1:"+t1);
//                File saveFile = new File(t1  + filename); // 定义一个file指向一个具体的文件
//                try {
//                    item.write(saveFile);// 把上传的内容写到一个文件中
//                } catch (Exception e) {
//
//                    System.out.println("文件为空");
//                }
//
//            }
//        }


        Map<String, String[]> map = request.getParameterMap();

        House house = new House();
        try {
            BeanUtils.populate(house,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }
        System.out.println(uid);
        Seller seller = sellerService.findSellerByUid(uid);
        house.setSid(seller.getSid());
        boolean flag = houseService.load(house);

        ResultInfo info = new ResultInfo();
        if(flag){

            info.setFlag(true);

        }else{

            info.setFlag(false);
            info.setErrorMsg("上传失败!");
        }


//        //将info对象序列化为json
//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(info);
//
//        //将json数据写回客户端
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(json);

        writeValue(info,response);

    }

    public void uploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        House house = houseService.lastHouse();

        int hid = house.getHid();
        System.out.println("hid:"+hid);
        ResultInfo resultInfo = new ResultInfo();
        FileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
        List items = null;
        try {
            items = servletFileUpload.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        Iterator iter = items.iterator();
        while(iter.hasNext()){
            FileItem item = (FileItem)iter.next();
            if (item.isFormField()){

            }else{
                String fieldName = item.getFieldName();
                String filename = item.getName();
                String t1 = request.getServletContext().getRealPath("/");
                if (fieldName.equals("himage")){
                    System.out.println("fieldname:"+fieldName);
                    String small =  "img/product/small/"+filename;
                    try {
                        File saveFile = new File(t1 + small); // 定义一个file指向一个具体的文件
                        item.write(saveFile);// 把上传的内容写到一个文件中
                        houseService.saveHimage(small,hid);

                    } catch (Exception e) {

                        resultInfo.setFlag(false);
                        resultInfo.setErrorMsg("上传失败");
                    }

                }

                if (fieldName.equals("images")){
                    System.out.println("fieldname:"+fieldName);
                    String bigPic = "img/product/size4/"+filename;
                    String smallPic = "img/product/size2/"+filename;


                    try {
                        File saveFile = new File(t1 + bigPic); // 定义一个file指向一个具体的文件
                        item.write(saveFile);// 把上传的内容写到一个文件中
                        houseService.saveHouseImg(hid,bigPic,smallPic);
                        resultInfo.setFlag(true);
                    } catch (Exception e) {

                        resultInfo.setFlag(false);
                        resultInfo.setErrorMsg("上传失败");
                    }

                    CopyUtil copyUtil = new CopyUtil();
                    copyUtil.copyFile(t1+bigPic,t1+smallPic);
                }



            }
        }

//        ObjectMapper mapper = new ObjectMapper();
//        String json = mapper.writeValueAsString(resultInfo);
//
//        //将json数据写回客户端
//        response.setContentType("application/json;charset=utf-8");
//        response.getWriter().write(json);
//        writeValue(resultInfo,response);

    }

    public void findAll( HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{

        List<House> houses = houseService.findAllHouse();

        writeValue(LayuiUtil.data(houses.size(),houses),response);
    }


    public void myHouse( HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String uidStr = request.getParameter("uid");

        int uid = 0;//类别id

        if(uidStr != null && uidStr.length() > 0 && !"null".equals(uidStr)){
            uid = Integer.parseInt(uidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }
        System.out.println("uid:"+uid);
        PageBean<House> pb = houseService.userHousePageQuery(uid,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);
    }

    public void deleteHouse( HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        System.out.println( "hid:"+request.getParameter("hid"));
//        houseService.deleteHouse(hid);

    }

    public void allHouse( HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 10;
        }

        PageBean<House> pb = houseService.allHousePageQuery(currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);
    }

    public void changeHflag(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hidStr = request.getParameter("hid");
        int hid = 0;//类别id
        boolean flag = false;
        ResultInfo info = new ResultInfo();
        String hflag = request.getParameter("hflag");

        if(hidStr != null && hidStr.length() > 0 && !"null".equals(hidStr)){
            hid = Integer.parseInt(hidStr);
        }
        if (hflag.equals("1") ){
            flag = houseService.offSale(hid);
            if(flag){

                info.setFlag(true);
            }else{

                info.setFlag(false);
                info.setErrorMsg("下架失败!");
            }
        } else if(hflag.equals("0")){
            flag = houseService.onSale(hid);
            if(flag){

                info.setFlag(true);
            }else{

                info.setFlag(false);
                info.setErrorMsg("上架失败!");
            }
        }

        //将info对象序列化为json
        String json = writeValueAsString(info);

        //将json数据写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    public void hotHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<House> houses = houseService.hotHouse();
        writeValue(houses,response);
    }

    public void newestHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<House> houses = houseService.newestHouse();
        writeValue(houses,response);
    }

    public void testHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<House> houses = houseService.testHouse();
        writeValue(houses,response);
    }

    public void abroadRecommendHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<House> houses = houseService.abroadRecommendHouse();
        writeValue(houses,response);
    }

    public void domesticRecommendHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<House> houses = houseService.domesticRecommendHouse();
        writeValue(houses,response);
    }

    public void siderHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<House> houses = houseService.siderHouse();
        writeValue(houses,response);
    }

    public void favoriteRank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String mixPriceStr = request.getParameter("mixPrice");
        String maxPriceStr = request.getParameter("maxPrice");
        String hname = request.getParameter("hname");
        if(hname!=null){
            hname = new String(hname.getBytes("iso-8859-1"),"utf-8");
        }


        int mixPrice = 0;//类别id
        int maxPrice = 1000;
        if(mixPriceStr != null && mixPriceStr.length() > 0 && !"null".equals(mixPriceStr)){
            mixPrice = Integer.parseInt(mixPriceStr);
        }
        if(maxPriceStr != null && maxPriceStr.length() > 0 && !"null".equals(maxPriceStr)){
            maxPrice = Integer.parseInt(maxPriceStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 10;
        }


        PageBean<House> pb = houseService.favoriteRank(hname,mixPrice,maxPrice,currentPage,pageSize);

        //将pageBean对象序列化为json
        writeValue(pb,response);
    }

    public void updateHouse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();

        House house = new House();
        try {
            BeanUtils.populate(house,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean flag = houseService.update(house);
        ResultInfo info = new ResultInfo();
        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("修改失败!");
        }

        writeValue(info,response);

    }
}
