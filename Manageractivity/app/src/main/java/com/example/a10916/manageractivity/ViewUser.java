package com.example.a10916.manageractivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class ViewUser extends AppCompatActivity {
    private  String result;
    private List<userbase> list;
    private List<String> infor;
    private ArrayAdapter adapter;
    private ListView listView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
         infor = new ArrayList<String>();
        editText = (EditText)findViewById(R.id.Uquery);
        Button btn_queryU = (Button)findViewById(R.id.btn_queryU);
         listView = (ListView)findViewById(R.id.listview);
        btn_queryU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = editText.getText().toString(); //得到输入结果
                //找到集合
                 list = DataSupport.where("stunumber = ?", result).find(userbase.class);
                for(userbase user:list){
                    //每次点击之前先把infor清空，不然点击查询会一直出现重复数据
                    infor.clear();
                    infor.add("专业:"+user.getProject() + "\t  班级:" + user.getClassroom()+"\n学号:" + user.getStunumber() + "\t  姓名:" + user.getName()+ "\n  身份证号:" + user.getIdcard());
                    adapter = new ArrayAdapter(ViewUser.this,android.R.layout.simple_list_item_1,infor);
                    listView.setAdapter(adapter);
                    //点击内容事件，点击哪一项就表示要修改哪一项内容
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            userbase user = list.get(i);
                            Toast.makeText(ViewUser.this,user.getName(),Toast.LENGTH_SHORT).show();
                            //这里想用自定义弹窗来实现
                            final MyDialog userDialog = new MyDialog(ViewUser.this);         //自己设置的xml布局
                            //设置对话框的图标
                           // userDialog.setIcon(R.mipmap.ic_launcher);
                            //设置标题
                          // userDialog.setTitle("用户信息");
                            //显示对话框
                            userDialog.show();
                            //通过MyDialog对象找到相关控件，不在本活动对应activity里面的xml，要找控件，需要在findViewById前加上或activity名

                            //得到数据库信息，展示到弹窗中
                            Button btn_ok = (Button) userDialog.findViewById(R.id.btn_ok_dialog);
                            Button btn_cancel = (Button) userDialog.findViewById(R.id.btn_cancel_dialog);
                            //设置学号
                            final EditText et_userStunum = (EditText) userDialog.findViewById(R.id.et_stunumber_dialog);
                            et_userStunum.setText(user.getStunumber());
                            //设置姓名
                            final EditText et_userName = (EditText) userDialog.findViewById(R.id.et_username_dialog);
                            et_userName.setText(user.getName());
                            //设置密码
                            final EditText et_pwd = (EditText) userDialog.findViewById(R.id.et_pwd_dialog);
                            et_pwd.setText(user.getPassword());
                            //设置身份证
                            final EditText et_idcard = (EditText) userDialog.findViewById(R.id.et_idcard_dialog);
                            et_idcard.setText(user.getIdcard());
                            //设置专业
                            final EditText et_zhuanye = (EditText) userDialog.findViewById(R.id.et_zhuanye_dialog);
                            et_zhuanye.setText(user.getProject());
                            //设置班级
                            final EditText et_banji = (EditText) userDialog.findViewById(R.id.et_banji_dialog);
                            et_banji.setText(user.getClassroom());

                            //
                            //设置按钮的监听事件
                            btn_ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String userName = et_userName.getText().toString();
                                    String userPwd = et_pwd.getText().toString();
                                    String stunumber = et_userStunum.getText().toString();
                                    String idcard = et_idcard.getText().toString();
                                    String project = et_zhuanye.getText().toString();
                                    String classroom = et_banji.getText().toString();
                                    //将当前页面数据保存到数据库
                                    userbase u = new userbase();

                                    u.setStunumber(stunumber);
                                    u.setName(userName);
                                    u.setPassword(userPwd);
                                    u.setIdcard(idcard);
                                    u.setProject(project);
                                    u.setClassroom(classroom);
                                    u.save();

                                    Toast.makeText(ViewUser.this, "保存数据成功", Toast.LENGTH_SHORT).show();
                                }
                            });
                            btn_cancel.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //回收对话框,什么都不干
                                    userDialog.dismiss();
                                }
                            });
                            //
                        }
                    });
                    //点击内容项事件结束
                }
                adapter.notifyDataSetChanged();//通知刷新
            }
        });
    }
}
