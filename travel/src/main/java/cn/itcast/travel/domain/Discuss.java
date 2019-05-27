package cn.itcast.travel.domain;

import java.io.Serializable;
import java.util.List;

public class Discuss implements Serializable {
    private int did;
    private int uid;
    private int aid;
    private String content;
    private String createDate;
    private String uname;

    private List<Reply> replyList;

    public Discuss(){

    }

    public Discuss(int did, int uid, int aid, String content, String createDate, String uname) {
        this.did = did;
        this.uid = uid;
        this.aid = aid;
        this.content = content;
        this.createDate = createDate;
        this.uname = uname;
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "did=" + did +
                ", uid=" + uid +
                ", aid=" + aid +
                ", content='" + content + '\'' +
                ", createDate='" + createDate + '\'' +
                ", uname='" + uname + '\'' +
                ", replyList=" + replyList +
                '}';
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }
}
