package com.example.a10916.manageractivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class forgetpassword extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        Button buttonforget = (Button) findViewById(R.id.buttonforget);
        buttonforget.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonforget:
                TextView textViewstu = (TextView) findViewById(R.id.forgetinputStun);
                TextView textViewidcard = (TextView) findViewById(R.id.forgetinputidcard);
                TextView textViewneapassword = (TextView)findViewById(R.id.forgetinputnewpassw);
                TextView textViewsurepassw = (TextView)findViewById(R.id.forgetinputsurepassw);
                //输入的学号
                String forgetstu = textViewstu.getText().toString();
                //输入的身份证号
                String forgetidcard = textViewidcard.getText().toString();
                //输入的新密码
                String forgetnewpass = textViewneapassword.getText().toString();
                //输入确认密码
                String forgetsurepass = textViewsurepassw.getText().toString();

                //查找数据
                List<userbase> list = DataSupport.select("Idcard")
                        .where("stunumber = ?",forgetstu)
                        .limit(1)
                        .find(userbase.class);

                for(userbase userbase :list){
                    //身份证和学号一致
                    if(userbase.getIdcard().equals(forgetidcard)){
                        //两次密码输入一致
                        if(forgetnewpass.equals(forgetsurepass)){
                            //重新存储新的密码
                            userbase.setPassword(forgetnewpass);
                            userbase.save();
                            Toast.makeText(view.getContext(),"找回成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        else {
                            //两次密码输入的不一致，文本框清空
                            textViewidcard.setText("");
                            textViewneapassword.setText("");
                            textViewstu.setText("");
                            textViewsurepassw.setText("");
                            Toast.makeText(view.getContext(),"两次密码输入的不一致",Toast.LENGTH_SHORT).show();

                        }
                    }
                    //身份证和学号不一致------重置
                    else {
                        //将文本框的数据都清空
                        textViewidcard.setText("");
                        textViewneapassword.setText("");
                        textViewstu.setText("");
                        textViewsurepassw.setText("");
                        Toast.makeText(view.getContext(),"身份证号或者学号不存在",Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }
}
