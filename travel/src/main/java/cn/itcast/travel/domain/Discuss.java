package cn.itcast.travel.domain;

import java.io.Serializable;

public class Discuss implements Serializable {
    private int did;
    private int uid;
    private int aid;
    private String content;
    private String createDate;
    private String forWho;

    public Discuss(){

    }

    public Discuss(int did, int uid, int aid, String content, String createDate, String forWho) {
        this.did = did;
        this.uid = uid;
        this.aid = aid;
        this.content = content;
        this.createDate = createDate;
        this.forWho = forWho;
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

    public String getForWho() {
        return forWho;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
    }
}
