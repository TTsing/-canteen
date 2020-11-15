package com.example.a10916.manageractivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class ViewUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        List<String> infor = new ArrayList<String>();
        //查询数据库中所有数据，并放在listview中进行显示
        List<userbase> list = DataSupport.findAll(userbase.class);
        for(userbase user:list){
            infor.add("专业:"+user.getProject() + "\t  班级:" + user.getClassroom()+"\n学号:" + user.getStunumber() + "\t  姓名:" + user.getName()+ "\n  身份证号:" + user.getIdcard());
        }
        ArrayAdapter adapter = new ArrayAdapter(ViewUser.this,android.R.layout.simple_list_item_1,infor);
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }
}
