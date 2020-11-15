package com.example.a10916.manageractivity;

import org.litepal.crud.DataSupport;

/**
 * Created by 10916 on 2020/11/14.
 */
//表ManagerInfor数据
public class Manager extends DataSupport{
    private int id;
    private String account;
    private String password;
    private String phone;

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId(){
        return id;
    }

    public String getAccount(){
        return account;
    }

    public String getPassword(){
        return  password;
    }

    public String getPhone(){
        return  phone;
    }
}
