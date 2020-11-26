package com.example.a10916.manageractivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class fragment extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        replaceFragment(new usermanager());

        //我的布局
        LinearLayout linearLayoutMine = (LinearLayout) findViewById(R.id.xiahuakuang3);
        linearLayoutMine.setOnClickListener(this);

        //排队布局
        LinearLayout linearLayoutinline = (LinearLayout) findViewById(R.id.xiahuakuang2);
        linearLayoutinline.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //下滑框我的的点击事件
            case R.id.xiahuakuang3:
                replaceFragment(new usermanager());
                break;
            //下滑框排队的点击事件
            case R.id.xiahuakuang2:
                replaceFragment(new inlineFragment());
                break;
            //下滑框今日的点击事件
            case R.id.xiahuakuang1:


        }
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.lokatsee,fragment);
        transaction.commit();
    }
}