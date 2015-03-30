package com.holyboom.flyer.health.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Doctor;
import com.holyboom.flyer.health.model.Patient;
import com.holyboom.flyer.health.model.User;

/**
 * Created by flyer on 15/3/17.
 */
public class RegisterActivity extends ActionBarActivity{


    LinearLayout regiserLayout;
    TextView userTypeTextView;
    EditText userId,userPwd;
    Button register;
    ImageButton doctorapers;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getUserInformationFromBundle();
        initUI();
        setClickListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

    /**
     * 获取用户信息
     */

    public void getUserInformationFromBundle(){
        if( getIntent().getSerializableExtra("user").getClass().equals(Patient.class)){
            user = new Patient();
            user = (Patient) getIntent().getSerializableExtra("user");
            android.util.Log.e("RegisterActivity 用户类型:", user.getClass() + "");
        }else {
            user = new Doctor();
            user = (Doctor) getIntent().getSerializableExtra("user");
            android.util.Log.e("RegisterActivity 用户类型:", user.getClass() + "");
        }
    }


    /**
     * 初始化控件
     */
    private void initUI(){
        regiserLayout = (LinearLayout) findViewById(R.id.register_layout);
        userTypeTextView = (TextView) findViewById(R.id.register_text_view);
        userId = (EditText) findViewById(R.id.register_user_id);
        userPwd = (EditText) findViewById(R.id.register_user_pwd);
        register = (Button) findViewById(R.id.register_button);
        if (user.getClass().equals(Patient.class)){
            userTypeTextView.setText("病人注册");
        }else {
            userTypeTextView.setText("医生注册");
        }
    }

    /**
     * 按钮监听
     */
    private void setClickListener(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InitUserRegisterInformation();
                uploadUserInformation();
                saveUserInformationToLocal();

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notification = new Notification(R.drawable.logo,"注册完成",System.currentTimeMillis());
                notification.setLatestEventInfo(RegisterActivity.this,"注册完成,正在登陆","账户注册完成",null);
                notificationManager.notify(1,notification);
                //加载progressbar
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });
    }

    /**
     * 根据getUserInformationFromBundle()
     * 以及用户输入的账号密码注册信息
     * 初始化用户信息
     */
    private void InitUserRegisterInformation(){}

    /**
     * 根据InitUserRegisterInformation
     * 上传用户信息
     */
    private void uploadUserInformation(){
        //此处有progressbar
    }

    /**
     * 保存用户信息到本地
     * 方便下次验证
     */
    private void saveUserInformationToLocal(){}
}
