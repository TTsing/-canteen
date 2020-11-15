package com.example.a10916.manageractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class Laoding extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laoding);
        TextView textViewchange = (TextView) findViewById(R.id.changepassword);
        Button buuton2 =(Button) findViewById(R.id.buttonloading);

        //登录
        buuton2.setOnClickListener(this);
        Button button = (Button) findViewById(R.id.buttonresigter);
        //测试用例添加事件
        button.setOnClickListener(this);

        //找回密码
        TextView textViewforget = (TextView) findViewById(R.id.forgetpassword);
        textViewforget.setOnClickListener(this);
        //修改密码
        textViewchange.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonloading:
                TextView textloading =(TextView) findViewById(R.id.inputloadingaccount);
                TextView textpassword = (TextView) findViewById(R.id.inputloadingpassword);
                String account = textloading.getText().toString();
                String password = textpassword.getText().toString();

                List<userbase> list = DataSupport.select("password")
                        .where("stunumber = ?",account)
                        .find(userbase.class);

                for(userbase userbase:list){
                    if(userbase.getPassword().equals(password)){
                        Toast.makeText(view.getContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        textloading.setText("");
                        textpassword.setText("");
                        Toast.makeText(view.getContext(),"密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.buttonresigter:
                LitePal.getDatabase();
                userbase userbase = new userbase();
                userbase.setName("刘江南");
                userbase.setStunumber("201831104022");
                userbase.setIdcard("410802200001300015");
                userbase.setProject("软件工程");
                userbase.setClassroom("软件工程1802班");
                userbase.setPassword(userbase.getIdcard().toString().substring(userbase.getIdcard().toString().length()-6,userbase.getIdcard().toString().length()));
                userbase.save();
                break;
            case R.id.changepassword:
                Intent intent = new Intent(Laoding.this,alterpassword.class);
                startActivity(intent);
                finish();
                break;
            case R.id.forgetpassword:
                Intent intent1 = new Intent(Laoding.this,forgetpassword.class);
                startActivity(intent1);
                finish();

        }
    }
}

