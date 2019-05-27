package cn.itcast.travel.domain;

import java.io.Serializable;

public class Reply implements Serializable {
    private int rid;
    private String uname;
    private int aid;
    private String forWho;
    private String content;

    private String createDate;
    public Reply(){

    }

    public Reply(int rid, String uname, int aid, String forWho, String content, String createDate) {
        this.rid = rid;
        this.uname = uname;
        this.aid = aid;
        this.forWho = forWho;
        this.content = content;
        this.createDate = createDate;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
