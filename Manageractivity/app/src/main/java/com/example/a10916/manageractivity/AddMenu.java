package com.example.a10916.manageractivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.Byte2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class AddMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        //获取控件
        Button btn_commit_food = (Button)findViewById(R.id.commit_food);
         final ImageView imageView_food = (ImageView)findViewById(R.id.Image_food);//图片
        final EditText editText_name = (EditText)findViewById(R.id.Name_food);//名称
        final EditText editText_price =(EditText)findViewById(R.id.Price_food);//价格
        //上传点击事件
        btn_commit_food.setOnClickListener(new View.OnClickListener() {

            String name_food = editText_name.getText().toString();
            String price_food = editText_price.getText().toString();
            @Override
            public void onClick(View view) {
                //获取到图片
              //  Bitmap headShot=BitmapFactory.decodeFile(imageView_food.toString());
                //把图片转化为字节流
              //  byte[]images=img(headShot);
                //找到Menus
                Menus menus = new Menus();
               //   menus.setImage(images);//存入图片二进制
                menus.setName(name_food);//存入名称
                menus.setPrice(price_food);//存入价格
                menus.save();//提交
            }
        });
    }
    //把图片转化为字节
    private byte[] img(Bitmap bitmap){ //bitmap是我们传入的图片
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }
}
