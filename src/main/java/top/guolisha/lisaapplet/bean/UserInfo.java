package top.guolisha.lisaapplet.bean;

import java.io.Serializable;


public class UserInfo implements Serializable {

    private static final long serialVersionUID = -8293476521902943999L;

    private long id;
    private String openId;
    private String nickName;
    private String language;
    private int gender;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String createTime;
    private String sessionid;

    public UserInfo(){

    }

    public UserInfo(long id, String openId, String nickName, String language, int gender, String city, String province, String country, String avatarUrl, String createTime, String sessionid) {
        this.id = id;
        this.openId = openId;
        this.nickName = nickName;
        this.language = language;
        this.gender = gender;
        this.city = city;
        this.province = province;
        this.country = country;
        this.avatarUrl = avatarUrl;
        this.createTime = createTime;
        this.sessionid = sessionid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", language='" + language + '\'' +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createTime='" + createTime + '\'' +
                ", sessionid='" + sessionid + '\'' +
                '}';
    }
}
