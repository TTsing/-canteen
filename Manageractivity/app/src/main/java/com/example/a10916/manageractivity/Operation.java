package com.example.a10916.manageractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//2.操作选择页面
public class Operation extends AppCompatActivity  implements View.OnClickListener{
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation);
        //找控件
        Button btn_manageUser = (Button)findViewById(R.id.op_manageUser);//管理用户
        Button btn_managerMenu = (Button)findViewById(R.id.op_managerMenu);// 管理菜单
        Button btn_manageMessage = (Button)findViewById(R.id.op_manageMessage);//推送消息
        Button btn_manageSystem = (Button)findViewById(R.id.op_manageSystem);//系统日志，接受用户反馈
        //设置事件
        btn_manageUser.setOnClickListener(this);
        btn_managerMenu.setOnClickListener(this);
        btn_manageMessage.setOnClickListener(this);
        btn_manageSystem.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.op_manageUser:
                intent = new Intent(Operation.this,ManagerUser.class);
                startActivity(intent);
                break;
            case R.id.op_managerMenu: //跳转到操作菜单页面
                intent = new Intent(Operation.this,ManagerMenu.class);
                startActivity(intent);
                break;
            case R.id.op_manageMessage:
                intent = new Intent(Operation.this,ManagerMessage.class);
                startActivity(intent);
                break;
            case R.id.op_manageSystem:
                break;
        }
    }
}
