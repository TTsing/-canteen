
package com.example.a10916.manageractivity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

public class ModifyUser extends AppCompatActivity {
private  EditText edxuehao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);
        edxuehao = (EditText)findViewById(R.id.mxuehao);
        Button modify = (Button)findViewById(R.id.modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String xuehao = edxuehao.getText().toString();//输入的学号
                //查询数据库中是否有该用户
                List<userbase> list = DataSupport.where("stunumber = ?", xuehao).find(userbase.class);
                if (list.isEmpty()) {
                    Toast.makeText(ModifyUser.this, "没有找到该学生有关信息!", Toast.LENGTH_LONG).show();
                }else{
                    //找到了信息
                    //使用自定义弹窗，在里面进行输入修改
                }
            }
        });
    }
}
