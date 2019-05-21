package cn.itcast.travel.domain;

import java.io.Serializable;

/**
 * 商家实体类
 */
public class Seller implements Serializable {
    private int sid;//卖家id
    private int uid;//用户id
    private String sname;//卖家名称
    private String consphone;//卖家电话
    private String address;//卖家地址
    private String identity;

    public Seller(){}



    public Seller(int sid, int uid, String sname, String consphone, String address, String identity) {

        this.sid = sid;
        this.uid = uid;
        this.sname = sname;
        this.consphone = consphone;
        this.address = address;
        this.identity = identity;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getConsphone() {
        return consphone;
    }

    public void setConsphone(String consphone) {
        this.consphone = consphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
