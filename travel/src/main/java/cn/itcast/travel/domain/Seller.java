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
    private String isSeller;
    private String createDate;
    private String updateDate;
    private String zfCode;
    private String wxCode;

    public Seller(){}


    public Seller(int sid, int uid, String sname, String consphone, String address, String identity, String isSeller, String createDate, String updateDate, String zfCode, String wxCode) {
        this.sid = sid;
        this.uid = uid;
        this.sname = sname;
        this.consphone = consphone;
        this.address = address;
        this.identity = identity;
        this.isSeller = isSeller;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.zfCode = zfCode;
        this.wxCode = wxCode;
    }

    public String getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(String isSeller) {
        this.isSeller = isSeller;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getZfCode() {
        return zfCode;
    }

    public void setZfCode(String zfCode) {
        this.zfCode = zfCode;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sid=" + sid +
                ", uid=" + uid +
                ", sname='" + sname + '\'' +
                ", consphone='" + consphone + '\'' +
                ", address='" + address + '\'' +
                ", identity='" + identity + '\'' +
                ", isSeller='" + isSeller + '\'' +
                '}';
    }
}
