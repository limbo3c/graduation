package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.SellerService;
import cn.itcast.travel.service.impl.SellerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/seller/*")
public class SellerServlet extends BaseServlet {

    private SellerService sellerService = new SellerServiceImpl();

    public void becomeSeller(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();

        //封装对象
        Seller seller = new Seller();

        try {
            BeanUtils.populate(seller,map);
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

        seller.setUid(uid);
        seller.setSname(user.getName());
        seller.setConsphone(user.getTelephone());

        ResultInfo info = new ResultInfo();

        boolean flag = sellerService.save(seller);
        //响应结果
        if(flag){

            info.setFlag(true);
        }else{

            info.setFlag(false);
            info.setErrorMsg("无法成为房东!");
        }

        //将info对象序列化为json
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        //写回客户端
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }


}
