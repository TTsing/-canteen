package com.example.a10916.manageractivity;

import org.litepal.crud.DataSupport;

/**
 * Created by 10916 on 2020/11/15.
 */
//表Menus内容
public class Menus extends DataSupport{
    private int id;
    private byte[] image; //图片二进制路径
    private String name;    //名称
    private String price;   //价格

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
