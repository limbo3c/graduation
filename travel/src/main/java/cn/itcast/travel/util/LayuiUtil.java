package cn.itcast.travel.util;

import cn.itcast.travel.domain.PageBean;

import java.util.HashMap;
import java.util.List;

public class LayuiUtil extends HashMap<String ,Object> {
    public static LayuiUtil data(Integer count,List<?> data){
        LayuiUtil r = new LayuiUtil();
        r.put("code", 0);
        r.put("msg", "");
        r.put("count", count);
        r.put("data", data);
        return r;
    }

}
