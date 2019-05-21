package cn.itcast.travel.domain;

import java.io.Serializable;

public class Comment implements Serializable {
    private int cmid;
    private String content;
    private int hid;
    private int uid;
    private String createTime;
    private String uname;

    public Comment(){}

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Comment(int cmid, String content, int hid, int uid, String createTime, String uname) {

        this.cmid = cmid;
        this.content = content;
        this.hid = hid;
        this.uid = uid;
        this.createTime = createTime;
        this.uname = uname;
    }

    public int getCmid() {
        return cmid;
    }

    public void setCmid(int cmid) {
        this.cmid = cmid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
