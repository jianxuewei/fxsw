package com.fxsw.models;

import java.io.Serializable;

/**
 * Created by 1 on 2015/10/26.
 */ //    private void initList() {
//        list.add("hello");
//        list .add("world");
//    }
public class Info implements Serializable {
    private String name;
    private int imgId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
    public Info(String name){
        this.name=name;
    }

    public Info(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
    }
}
