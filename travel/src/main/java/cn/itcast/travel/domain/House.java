package cn.itcast.travel.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 房源实体类
 */
public class House implements Serializable {

    private int hid;//id
    private String hname;//名称
    private double price;//价格
    private String houseIntroduce;//介绍
    private String hflag;   //是否上架，必输，0代表没有上架，1代表是上架
    private String hdate;   //上架时间
    private String inChina;
    private int count;//收藏数
    private int cid;//所属分类
    private String himage;//图片
    private int sid;//房东
    private String sourceId;
    private int peopleNumber;//宜住人数
    private int houseNumber;//数量
    private String city;
    private String location;
    private float score;

    private Category category;//所属分类
    private Seller seller;//所属商家
    private List<HouseImg> houseImgList;//图片集




    /**
     * 无参构造方法
     */
    public House(){}


    public House(int hid, String hname, double price, String houseIntroduce, String hflag, String hdate, String inChina, int count, int cid, String himage, int sid, String sourceId, int peopleNumber, int houseNumber, String city, String location, float score) {
        this.hid = hid;
        this.hname = hname;
        this.price = price;
        this.houseIntroduce = houseIntroduce;
        this.hflag = hflag;
        this.hdate = hdate;
        this.inChina = inChina;
        this.count = count;
        this.cid = cid;
        this.himage = himage;
        this.sid = sid;
        this.sourceId = sourceId;
        this.peopleNumber=peopleNumber;
        this.houseNumber=houseNumber;
        this.city=city;
        this.location=location;
        this.score=score;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getHouseIntroduce() {
        return houseIntroduce;
    }

    public void setHouseIntroduce(String houseIntroduce) {
        this.houseIntroduce = houseIntroduce;
    }

    public String getHflag() {
        return hflag;
    }

    public void setHflag(String hflag) {
        this.hflag = hflag;
    }

    public String getHdate() {
        return hdate;
    }

    public void setHdate(String hdate) {
        this.hdate = hdate;
    }

    public String getInChina() {
        return inChina;
    }

    public void setInChina(String inChina) {
        this.inChina = inChina;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getHimage() {
        return himage;
    }

    public void setHimage(String himage) {
        this.himage = himage;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(int peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<HouseImg> getHouseImgList() {
        return houseImgList;
    }

    public void setHouseImgList(List<HouseImg> houseImgList) {
        this.houseImgList = houseImgList;
    }

    @Override
    public String toString() {
        return "House{" +
                "hid=" + hid +
                ", hname='" + hname + '\'' +
                ", price=" + price +
                ", houseIntroduce='" + houseIntroduce + '\'' +
                ", hflag='" + hflag + '\'' +
                ", hdate='" + hdate + '\'' +
                ", inChina='" + inChina + '\'' +
                ", count=" + count +
                ", cid=" + cid +
                ", himage='" + himage + '\'' +
                ", sid=" + sid +
                ", sourceId='" + sourceId + '\'' +
                ", peopleNumber=" + peopleNumber +
                ", houseNumber=" + houseNumber +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", score=" + score +
                ", category=" + category +
                ", seller=" + seller +
                ", houseImgList=" + houseImgList +
                '}';
    }
}
