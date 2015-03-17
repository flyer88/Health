package com.holyboom.flyer.health.Activity;

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
        initUI();
        getUserImformation();
        setClickListener();
    }

    /**
     * 获取用户信息
     */

    public void getUserImformation(){
        user = (User) getIntent().getSerializableExtra("user");
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
    }

    /**
     * 按钮监听
     */
    private void setClickListener(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                //直接登陆
                startActivity(intent);
            }
        });
    }
}
