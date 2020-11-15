package com.example.a10916.manageractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//对菜单的操作的活动页面
public class ManagerMenu extends AppCompatActivity implements View.OnClickListener{
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_menu);
        //获得控件
        Button btn_add = (Button)findViewById(R.id.Add_Menu);
        Button btn_delete = (Button)findViewById(R.id.Delete_Menu);
        Button btn_modify = (Button)findViewById(R.id.Modify_Menu);
        Button btn_view = (Button)findViewById(R.id.View_Menu);
        //添加点击事件
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_modify.setOnClickListener(this);
        btn_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Add_Menu:
                //跳转到添加菜单页面
                intent = new Intent(ManagerMenu.this,AddMenu.class);
                startActivity(intent);
                break;
            case R.id.Delete_Menu:
                break;
            case R.id.Modify_Menu:
                break;
            case R.id.View_Menu:
                break;
        }
    }
}
