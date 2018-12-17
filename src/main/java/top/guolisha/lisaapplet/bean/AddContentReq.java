package top.guolisha.lisaapplet.bean;

import java.io.Serializable;

public class AddContentReq implements Serializable {
    private static final long serialVersionUID = -8293476521902943999L;

    private long id;
    private String sessionid;
    private String title;
    private String imgpath;
    private String content;

   public AddContentReq(){}

    public AddContentReq(long id, String sessionid, String title, String imgpath, String content) {
        this.id = id;
        this.sessionid = sessionid;
        this.title = title;
        this.imgpath = imgpath;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    @Override
    public String toString() {
        return "AddContentReq{" +
                "id=" + id +
                ", sessionid='" + sessionid + '\'' +
                ", title='" + title + '\'' +
                ", imgpath='" + imgpath + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
