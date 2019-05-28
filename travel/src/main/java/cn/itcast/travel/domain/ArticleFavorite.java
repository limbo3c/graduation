package cn.itcast.travel.domain;

public class ArticleFavorite {
    private int aid;
    private int uid;
    private String date;

    public ArticleFavorite(){

    }

    public ArticleFavorite(int aid, int uid, String date) {
        this.aid = aid;
        this.uid = uid;
        this.date = date;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
