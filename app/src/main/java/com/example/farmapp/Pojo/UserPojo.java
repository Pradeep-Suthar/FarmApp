package com.example.farmapp.Pojo;

public class UserPojo {
    private String Name;
    private String Email;
    private String Moble_no;
    private String Pwd;
    private String id;
    private String img;
    private String pinCode;

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String ConPwd;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMoble_no() {
        return Moble_no;
    }

    public void setMoble_no(String moble_no) {
        Moble_no = moble_no;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getConPwd() {
        return ConPwd;
    }

    public void setConPwd(String conPwd) {
        ConPwd = conPwd;
    }
}
