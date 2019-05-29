package cn.itcast.travel.domain;

import java.io.Serializable;

public class Article implements Serializable {
    private int aid;
    private int author;
    private String title;
    private String story;
    private String aimage;
    private String createDate;
    private int fabulous;
    private String authorName;
    private String updateDate;



    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Article(){

    }

    public Article(int aid, int author, String title, String story, String aimage, String createDate, int fabulous, String authorName, String updateDate) {
        this.aid = aid;
        this.author = author;
        this.title = title;
        this.story = story;
        this.aimage = aimage;
        this.createDate = createDate;
        this.fabulous = fabulous;
        this.authorName = authorName;
        this.updateDate = updateDate;
    }

    public int getFabulous() {
        return fabulous;
    }

    public void setFabulous(int fabulous) {
        this.fabulous = fabulous;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
