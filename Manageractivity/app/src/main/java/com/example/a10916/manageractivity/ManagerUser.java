package com.example.a10916.manageractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerUser extends AppCompatActivity implements View.OnClickListener{
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_user);
        //获得控件
        Button btn_add = (Button)findViewById(R.id.Add_User);
        Button btn_delete = (Button)findViewById(R.id.Delete_User);
      //  Button btn_modify = (Button)findViewById(R.id.Modify_User);
        Button btn_view = (Button)findViewById(R.id.View_User);
        //添加点击事件
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
     //   btn_modify.setOnClickListener(this);
        btn_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.Add_User:
                //跳转到添加菜单页面
                intent = new Intent(ManagerUser.this, AddUser.class);
                startActivity(intent);
                break;
            case R.id.Delete_User:
                intent = new Intent(ManagerUser.this, DeleteUser.class);
                startActivity(intent);
                break;
//            case R.id.Modify_User:
//                intent = new Intent(ManagerUser.this, ModifyUser.class);
//                startActivity(intent);
//                break;
            case R.id.View_User:
                intent = new Intent(ManagerUser.this, ViewUser.class);
                startActivity(intent);
                break;
        }
    }
}
