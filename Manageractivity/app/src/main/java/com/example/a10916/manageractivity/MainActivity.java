package com.example.a10916.manageractivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

//1.登录页面
public class MainActivity extends AppCompatActivity {
    private String Maccount;
    private String Mpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建数据库
        LitePal.getDatabase();
        Manager manager = new Manager();
        manager.setAccount("Admin");
        manager.setPassword("000000");
        manager.setPhone("15215087934");
        manager.save();
        //获取控件
        Button btn_login = (Button)findViewById(R.id.Login);
        final EditText account = (EditText)findViewById(R.id.zhanghu);
        final EditText password = (EditText)findViewById(R.id.mima);
        //事件
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Maccount = account.getText().toString();//输入的账户
                Mpassword = password.getText().toString();//输入的密码
                if (Maccount.equals("") || Mpassword.equals("")) {
                    Toast.makeText(MainActivity.this, "输入不能为空！", Toast.LENGTH_LONG).show();
                } else {
                    List<Manager> managers = DataSupport.where("account = ? and password = ?", Maccount, Mpassword).find(Manager.class);
                    //打印数据，方便检查
                    for (Manager m : managers) {
                        Log.d("MainActivity", m.getAccount());
                        Log.d("MainActivity", m.getPassword());
                    }
                    //判断是否查出
                    if (!managers.isEmpty()) {
                        Intent intent = new Intent(MainActivity.this, Operation.class);
                        Toast.makeText(MainActivity.this, "管理员登录成功,进入操作选择页面！", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    } else {
                        password.setText(""); //情况密码栏
                        Toast.makeText(MainActivity.this, "（LitePal）登录失败！密码或账号错误，请重新输入", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
