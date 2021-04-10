package cn.edu.nbut.jerry.POJO;

import java.util.Date;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private String nickname;
    private String sex;
    private String portrait;
    private Date registerDate;
    private String phoneNumber;
    private String idType;
    private String idNumber;
    private String province;
    private String city;
    private String area;
    private String address;

    public User() {
    }

    public User(String username, String password, String nickname, String sex, String portrait, Date registerDate, String phoneNumber, String idType, String idNumber, String province, String city, String area, String address) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.portrait = portrait;
        this.registerDate = registerDate;
        this.phoneNumber = phoneNumber;
        this.idType = idType;
        this.idNumber = idNumber;
        this.province = province;
        this.city = city;
        this.area = area;
        this.address = address;
    }

    public User(Integer userId, String username, String password, String nickname, String sex, String portrait, Date registerDate, String phoneNumber, String idType, String idNumber, String province, String city, String area, String address) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.sex = sex;
        this.portrait = portrait;
        this.registerDate = registerDate;
        this.phoneNumber = phoneNumber;
        this.idType = idType;
        this.idNumber = idNumber;
        this.province = province;
        this.city = city;
        this.area = area;
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", portrait='" + portrait + '\'' +
                ", registerDate=" + registerDate +
                ", telephone='" + phoneNumber + '\'' +
                ", idType='" + idType + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
