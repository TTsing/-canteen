package com.example.a10916.manageractivity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddUser extends AppCompatActivity {
    private String zhuanye;
    private String banji;
    private String xuehao;
    private String xingming;
    private String shenfen;
    private String mima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Button btn_commit_user = (Button)findViewById(R.id.commit_user);
         final EditText editText_zhuanye = (EditText)findViewById(R.id.inputlresigteragpassword);//专业
         final EditText editText_banji = (EditText)findViewById(R.id.inputlresigterstunumber);//班级
         final EditText editText_id = (EditText)findViewById(R.id.inputlresigteraccount);//学号
         final EditText editText_name =(EditText)findViewById(R.id.inputlresigtername);//姓名
        final EditText editText_mima = (EditText)findViewById(R.id.inputlresigterpassword);//初始化密码
        final EditText editText_card = (EditText)findViewById(R.id.inputlresigteridcard); //身份证号

        btn_commit_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuehao = editText_id.getText().toString();//输入的学号
                xingming = editText_name.getText().toString();//输入的名字
                zhuanye = editText_zhuanye.getText().toString();//输入的专业
                banji = editText_banji.getText().toString();//输入的班级
                shenfen = editText_card.getText().toString();//输入的身份证号
                mima = editText_mima.getText().toString();//输入的用户初始密码
                if (xuehao.equals("") || xingming.equals("") || zhuanye.equals("") || banji.equals("") || shenfen.equals("") || mima.equals("") ) {
                    Toast.makeText(AddUser.this, "输入不能为空！", Toast.LENGTH_LONG).show();
                }else{
                    //存入数据库表Users中
                    userbase userbase = new userbase();
                    userbase.setName(xingming);
                    userbase.setStunumber(xuehao);
                    userbase.setIdcard(shenfen);
                    userbase.setProject(zhuanye);
                    userbase.setClassroom(banji);
                    userbase.setPassword(mima);
                    userbase.save();
                    Toast.makeText(AddUser.this, "添加成功！", Toast.LENGTH_LONG).show();
                     new AlertDialog.Builder(AddUser.this)
                             .setIcon(R.drawable.launcher_icon)
                             .setTitle("注意")
                             .setMessage("继续添加吗")
                             .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
                                     editText_zhuanye.setText("");
                                     editText_banji.setText("");
                                     editText_name.setText("");
                                     editText_id.setText("");
                                     editText_mima.setText("");
                                     editText_card.setText("");
                                 }
                             })
                             .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialog, int which) {
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
