package cn.itcast.travel.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {
    private int oid;     //订单id

    private int sid;     //房东

    private int uid;      //房客

    private String name;    //名称

    private String telephone; //电话

    private String identity; //身份证

    private int hid;      //预订房

    private double price;  //订单金额

    private String startAndEndDate;   //开始和结束日期

    private Integer pay;    //支付状态
    //1.未支付
    //2.已支付

    private Integer stage;     //订单状态
    //1.进行中
    //2.已完结
    //3.退订

    private String createDate;    //创建时间

    private String updateDate;    //修改时间

    private float score;    //评分

//    private House house;
//    private Seller seller;

    public Order(){

    }

    public Order(int oid,int sid,int uid,int hid,double price,String startAndEndDate,Integer pay,Integer stage,String createDate,String updateDate,float score){

        this.oid=oid;
        this.sid=sid;
        this.uid=uid;
        this.hid=hid;
        this.price=price;
        this.startAndEndDate=startAndEndDate;
        this.pay=pay;
        this.stage=stage;
        this.createDate=createDate;
        this.updateDate=updateDate;
        this.score=score;



    }

    public int getOid() {
        return oid;
    }

    public int getSid() {
        return sid;
    }

    public int getUid() {
        return uid;
    }

    public int getHid() {
        return hid;
    }

    public double getPrice() {
        return price;
    }

    public String getStartAndEndDate() {
        return startAndEndDate;
    }

    public Integer getPay() {
        return pay;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Integer getStage() {
        return stage;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartAndEndDate(String startAndEndDate) {
        this.startAndEndDate = startAndEndDate;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getIdentity() {
        return identity;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getScore() {
        return score;
    }
}

