package com.jtthink.entity;

public class UserInfo {

    private String user_name;
    private String user_pass;
    private int user_type; //0 老师  1 学生

    public UserInfo(){ //必须要有这个空构造函数，否则转换的时候报错

    }

    public UserInfo(String user_name, String user_pass, int user_type) {
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.user_type = user_type;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user_name='" + user_name + '\'' +
                ", user_pass='" + user_pass + '\'' +
                ", user_type=" + user_type +
                '}';
    }
}
