package com.example.a10916.manageractivity;

import org.litepal.crud.DataSupport;

/**
 * Created by HP on 2020/11/15.
 */

public class userbase extends DataSupport {

    //姓名
    private String Name;

    //学号
    private String stunumber;

    //密码
    private String password;

    //身份证
    private String Idcard;

    //专业
    private String project;

    //班级
    private String classroom;
    



    //得到姓名
    public String getName(){
        return Name;
    }

    //更改姓名
    public void setName(String Name){
        this.Name = Name;
    }

    //得到学号
    public String getStunumber(){
        return stunumber;
    }
    //更改学号
    public void setStunumber(String stunumber){
        this.stunumber = stunumber;
    }
    //得到密码
    public String getPassword(){
        return password;
    }

    //更改密码
    public void setPassword(String password){
        this.password = password;
    }
    //得到身份证
    public String getIdcard(){
        return Idcard;
    }
    //更改身份证
    public void setIdcard(String idcard){
        this.Idcard = idcard;
    }

    //获得专业
    public String getProject(){
        return project;
    }
    //修改专业信息
    public void setProject(String project){
        this.project = project;
    }
    //获得班级
    public String getClassroom(){
        return classroom;
    }

    //修改班级类型
    public void setClassroom(String classroom){
        this.classroom= classroom;
    }
}
