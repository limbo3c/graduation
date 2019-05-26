package cn.itcast.travel.domain;

import java.io.Serializable;

public class Reply implements Serializable {
    private int rid;
    private int uid;
    private int aid;
    private String forWho;
    private String content;
    private int forDiscuss;
    private String createDate;

    public Reply(int rid, int uid, int aid, String forWho, String content, int forDiscuss, String createDate) {
        this.rid = rid;
        this.uid = uid;
        this.aid = aid;
        this.forWho = forWho;
        this.content = content;
        this.forDiscuss = forDiscuss;
        this.createDate = createDate;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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

    public String getForWho() {
        return forWho;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getForDiscuss() {
        return forDiscuss;
    }

    public void setForDiscuss(int forDiscuss) {
        this.forDiscuss = forDiscuss;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
