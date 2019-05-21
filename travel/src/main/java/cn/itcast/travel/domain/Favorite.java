package cn.itcast.travel.domain;

import java.io.Serializable;

/**
 * 收藏实体类
 */
public class Favorite implements Serializable {
//    private Route route;//对象
    private String date;//收藏时间
//    private User user;//所属用户
    private int hid;
    private int uid;

    public Favorite() {
    }

    public Favorite(String date, int hid, int uid) {
        this.date = date;
        this.hid = hid;
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
//    public Favorite(Route route, String date, User user) {
//            this.route = route;
//            this.date = date;
//            this.user = user;
//    }
//
//    public Route getRoute() {
//        return route;
//    }
//
//    public void setRoute(Route route) {
//        this.route = route;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


}
