package com.example.a10916.manageractivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class MyDialog extends AlertDialog {
    private Context context;
    protected MyDialog(@NonNull Context context) {
        super(context, R.style.MyDialog);
        this.context = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //通过LayoutInflater来获取布局文件对象
        LayoutInflater inflater = LayoutInflater.from(context);
        View userDialog = inflater.inflate(R.layout.user_dialog_layout, null);
        setView(userDialog);
        super.onCreate(savedInstanceState);
    }
}
