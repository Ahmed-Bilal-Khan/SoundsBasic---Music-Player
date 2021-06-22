package com.example.soundsbasic.Models;

public class Settings {
    private int imgv1;
    private String itemname1,itemname2;

    public Settings() {
    }

    public Settings(int imgv1, String itemname1, String itemname2) {
        this.imgv1 = imgv1;
        this.itemname1 = itemname1;
        this.itemname2 = itemname2;
    }

    public int getImgv1() {
        return imgv1;
    }

    public void setImgv1(int imgv1) {
        this.imgv1 = imgv1;
    }

    public String getItemname1() {
        return itemname1;
    }

    public void setItemname1(String itemname1) {
        this.itemname1 = itemname1;
    }

    public String getItemname2() {
        return itemname2;
    }

    public void setItemname2(String itemname2) {
        this.itemname2 = itemname2;
    }
}
