package com.example.a10916.manageractivity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class DeleteUser extends AppCompatActivity {
    private EditText id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        id = (EditText)findViewById(R.id.dxuehao);
        Button delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String xuehao = id.getText().toString();//输入的学号
                //查询数据库中是否有该用户
                List<userbase> list = DataSupport.where("stunumber = ?", xuehao).find(userbase.class);
                if (list.isEmpty()) {
                    Toast.makeText(DeleteUser.this, "没有找到该学生有关信息!", Toast.LENGTH_LONG).show();
                } else {
                    //找到
                    new AlertDialog.Builder(DeleteUser.this)
                            .setIcon(R.drawable.launcher_icon)
                            .setTitle("注意")
                            .setMessage("确定删除该用户吗")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DataSupport.deleteAll(userbase.class,"stunumber = ?",xuehao); //删除
                                    finish();
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //什么都不做，关闭弹出框
                                    finish();
                                }
                            })
                            .create()
                            .show();
                }
            }
        });
    }
}
