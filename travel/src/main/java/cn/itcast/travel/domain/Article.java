package cn.itcast.travel.domain;

import java.io.Serializable;

public class Article implements Serializable {
    private int aid;
    private int uid;
    private String title;
    private String story;
    private String aimage;
    private String createDate;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Article(int aid, int uid, String title, String story, String aimage, String createDate) {

        this.aid = aid;
        this.uid = uid;
        this.title = title;
        this.story = story;
        this.aimage = aimage;
        this.createDate = createDate;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getAimage() {
        return aimage;
    }

    public void setAimage(String aimage) {
        this.aimage = aimage;
    }
}
