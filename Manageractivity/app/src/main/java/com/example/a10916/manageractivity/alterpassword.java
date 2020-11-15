package com.example.a10916.manageractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class alterpassword extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterpassword);
        Button button = (Button) findViewById(R.id.buttonchange);
        //修改成功按钮的点击时间
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView textviewstu = (TextView) findViewById(R.id.alterinputStun);
        TextView textViewpassw = (TextView) findViewById(R.id.alterinputpassword);
        TextView textViewnewpass = (TextView) findViewById(R.id.alterinputnewpassw);
        TextView textViewsurepass = (TextView) findViewById(R.id.alterinputsurepassw);
        TextView textViewtele = (TextView)findViewById(R.id.alterinputtelep);
        String stuname = textviewstu.getText().toString();//学号
        String password = textViewpassw.getText().toString();//原来密码
        String newpassword = textViewnewpass.getText().toString();//新密码
        String surepassword = textViewsurepass.getText().toString();
        //判断密码和学号是否对应

        List<userbase> list = DataSupport
                .select("password")
                .where("stunumber = ?",stuname)
                .limit(1)
                .find(userbase.class);
        for(userbase userbase :list) {

            //原来密码输入正确
            if (userbase.getPassword().equals(password)) {
                //更改密码时两次输入的密码一致
                if (newpassword.equals(surepassword)) {
                    userbase.setPassword(newpassword);
                    userbase.save();
                    Toast.makeText(view.getContext(), "修改成功", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(alterpassword.this,Laoding.class);
                    startActivity(intent);
                    finish();
                }
                //更改密码时两次输入的密码不一致
                else{
                    textViewnewpass.setText("");
                    textViewsurepass.setText("");
                    Toast.makeText(view.getContext(),"两次密码输入不一致或不能与新密码一样",Toast.LENGTH_SHORT).show();
                }
            }
            //原密码输入的不正确
            else{
                textViewpassw.setText("");
                textViewnewpass.setText("");
                textViewsurepass.setText("");
                Toast.makeText(view.getContext(),"密码输入的不正确或者账号不存在",Toast.LENGTH_SHORT).show();
            }

        }


    }
}
