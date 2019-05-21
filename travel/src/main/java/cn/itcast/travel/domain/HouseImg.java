package cn.itcast.travel.domain;

import java.io.Serializable;

/**
 * 图片实体类
 */
public class HouseImg implements Serializable {
    private int hgid;//图片id
    private int hid;//id
    private String bigPic;//大图
    private String smallPic;//小图


    public HouseImg() {
    }

    public HouseImg(int hgid, int hid, String bigPic, String smallPic) {
        this.hgid = hgid;
        this.hid = hid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public int getHgid() {
        return hgid;
    }

    public void setHgid(int hgid) {
        this.hgid = hgid;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
