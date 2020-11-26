package com.example.a10916.manageractivity;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.renderscript.Byte2;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.jar.Manifest;

public class AddMenu extends AppCompatActivity {
    private EditText editTextName,editTextPrice;
    private Button upDishes,clear,back;
    private String TAG = "mytag";
    private userbase tempUser;

    public  static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    private ImageView pictrue;
    private File outputImage;
    private Uri imageUri;
    private  static String filePath;
    String imagePath = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);




        //获取控件
        upDishes = (Button) findViewById(R.id.upDishes_u); //上传按钮
        clear = (Button)findViewById(R.id.clear_u); //清空按钮
        back = (Button)findViewById(R.id.back_u);   //取消按钮
        editTextName = (EditText)findViewById(R.id.Name_food);  //食物名
        editTextPrice = (EditText)findViewById(R.id.Price_food);    //价格
        //图片处理
        Button takePhoto = (Button)findViewById(R.id.take_photo); //照相按钮
        Button chooseFromAlbum = (Button)findViewById(R.id.choose_from_album);//从相册选取图片
        pictrue = (ImageView) findViewById(R.id.picture);


        //
        //测试从数据库取图片

      //  Menus mUser=DataSupport.findFirst(Menus.class);
      //  byte[]images=mUser.getImage();
      //  Bitmap bitmap=BitmapFactory.decodeByteArray(images,0,images.length);
       // pictrue.setImageBitmap(bitmap);
        //
        //

        //直接用手机拍照
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建file对象，用于存储拍照后的图片
                outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT >= 24){
                    imageUri = FileProvider.getUriForFile(AddMenu.this,"com.example.a10916.manageractivity.fileprovider",outputImage);
                }else{
                    imageUri = Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });
        //从相册中选取照片
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(AddMenu.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AddMenu.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    //若已经赋予相机权限，直接运行方法openAlbum
                    openAlbum();
                }
            }
        });

        //上传按钮
        upDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取到图片
                Bitmap headShot=BitmapFactory.decodeFile(imagePath);
                Log.d("AA2",imagePath);
                //把图片转换字节流
                byte[]images=img(headShot);
                if(images ==null){
                    Toast.makeText(AddMenu.this,"空的",Toast.LENGTH_SHORT).show();
                }else {
                    //找到菜单
                    Menus menus = new Menus();
                    //保存到数据库
                    menus.setImage(images);
                    menus.setName(editTextName.getText().toString());
                    menus.setPrice(editTextPrice.getText().toString());
                    menus.save();
                    Toast.makeText(AddMenu.this,"上传成功！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //清空按钮
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextName.setText("");
                editTextPrice.setText("");
            }
        });

        //取消按钮
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    //图片处理的方法
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO); //打开相册
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1 :
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try {
                        //将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        pictrue.setImageBitmap(bitmap);
                        imagePath=outputImage.getAbsolutePath();//此时获得绝对路径（不确定到底是否应当获取相对/绝对路径）
                       /* File dishPicture=new File(getExternalCacheDir(),"id_image.jpg");//id为菜品编号
                        if(dishPicture.exists()){
                            dishPicture.delete();
                        }
                        dishPicture = new Compressor(this)
                                .setMaxWidth(400)
                                .setMaxHeight(300)
                                .setQuality(70)
                                .compressToFile(outputImage);//压缩*/
                        //Bitmap bm=BitmapFactory.decodeFile(dishPicture.getAbsolutePath());
                        //picture_compress.setImageBitmap(bm);
                        //showPath.setText(dishPicture.getAbsolutePath());
                        ///等下存入数据库

                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK){
                    //判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19){
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    }else {
                        //4.4以下系统使用这个放出处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this,uri)){
            //如果是document类型的Uri,则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri,null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath);//根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor != null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath){
        if (imagePath != null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            pictrue.setImageBitmap(bitmap);
            filePath=imagePath;
            //File compressedPicFile=saveCroppedImage(new File(imagePath));
            //Bitmap bm=BitmapFactory.decodeFile(compressedPicFile.getAbsolutePath());
            // picture_compress.setImageBitmap(bm);
            //showPath.setText(compressedPicFile.getAbsolutePath());

            Log.d("AA1",imagePath);

        }else {
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
    //把图片转为二进制
    private byte[]img(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


}
