package com.jtthink.entity;

public class ExamDetail {

    private int detail_id;
    private int detail_parent;
    private String detail_parent_name;
    private int detail_son;
    private String detail_son_name;
    private int detail_grandson;
    private String detail_grandson_name;
    private int detail_result; //初始结果
    private int detail_score;
    private int detail_switch_use; //开关是否使用
    private String detail_switch_car_id; //开关关联id
    private String detail_step;
    private String detail_backup1; //答题是否正确 初始0 正确1 不正确2
    private String detail_backup2;

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public int getDetail_parent() {
        return detail_parent;
    }

    public void setDetail_parent(int detail_parent) {
        this.detail_parent = detail_parent;
    }

    public String getDetail_parent_name() {
        return detail_parent_name;
    }

    public void setDetail_parent_name(String detail_parent_name) {
        this.detail_parent_name = detail_parent_name;
    }

    public int getDetail_son() {
        return detail_son;
    }

    public void setDetail_son(int detail_son) {
        this.detail_son = detail_son;
    }

    public String getDetail_son_name() {
        return detail_son_name;
    }

    public void setDetail_son_name(String detail_son_name) {
        this.detail_son_name = detail_son_name;
    }

    public int getDetail_grandson() {
        return detail_grandson;
    }

    public void setDetail_grandson(int detail_grandson) {
        this.detail_grandson = detail_grandson;
    }

    public String getDetail_grandson_name() {
        return detail_grandson_name;
    }

    public void setDetail_grandson_name(String detail_grandson_name) {
        this.detail_grandson_name = detail_grandson_name;
    }

    public int getDetail_result() {
        return detail_result;
    }

    public void setDetail_result(int detail_result) {
        this.detail_result = detail_result;
    }

    public int getDetail_score() {
        return detail_score;
    }

    public void setDetail_score(int detail_score) {
        this.detail_score = detail_score;
    }

    public int getDetail_switch_use() {
        return detail_switch_use;
    }

    public void setDetail_switch_use(int detail_switch_use) {
        this.detail_switch_use = detail_switch_use;
    }

    public String getDetail_switch_car_id() {
        return detail_switch_car_id;
    }

    public void setDetail_switch_car_id(String detail_switch_car_id) {
        this.detail_switch_car_id = detail_switch_car_id;
    }

    public String getDetail_step() {
        return detail_step;
    }

    public void setDetail_step(String detail_step) {
        this.detail_step = detail_step;
    }

    public String getDetail_backup1() {
        return detail_backup1;
    }

    public void setDetail_backup1(String detail_backup1) {
        this.detail_backup1 = detail_backup1;
    }

    public String getDetail_backup2() {
        return detail_backup2;
    }

    public void setDetail_backup2(String detail_backup2) {
        this.detail_backup2 = detail_backup2;
    }
}
