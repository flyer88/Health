package com.holyboom.flyer.health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/3/17.
 */
public class LoginActivity extends ActionBarActivity{


    ViewPager loginViewPager;
    List<View> loginViewPagerList = new ArrayList<View>() ;
    EditText patientUserId,patientUserPwd,doctorUserId,doctorUserPwd;
    Button patientLogin,doctorLogin,patientRegister,doctorRegister;
    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        setClickListener();
    }


    /**
     * 初始化UI
     */
    private void initUI(){
        loginViewPager = (ViewPager) findViewById(R.id.login_view_pager);
        LayoutInflater layoutInflater = getLayoutInflater().from(this);
        View item1 = layoutInflater.inflate(R.layout.patient_login_item,null);
        View item2 = layoutInflater.inflate(R.layout.doctor_login_item,null);
        patientLogin = (Button) item1.findViewById(R.id.login_patient_button);
        patientRegister = (Button) item1.findViewById(R.id.to_register_patient_button);
        doctorLogin = (Button) item2.findViewById(R.id.login_doctor_button);
        doctorRegister = (Button) item2.findViewById(R.id.to_register_doctor_button);
        patientUserId = (EditText) item1.findViewById(R.id.patient_user_id);
        patientUserPwd = (EditText) item1.findViewById(R.id.patient_user_pwd);
        doctorUserId = (EditText) item2.findViewById(R.id.doctor_user_id);
        doctorUserPwd = (EditText) item2.findViewById(R.id.doctor_user_pwd);
        loginViewPagerList.add(item1);
        loginViewPagerList.add(item2);
        LoginViewPagerAdapter loginViewPagerAdapter = new LoginViewPagerAdapter();
        loginViewPager.setAdapter(loginViewPagerAdapter);
    }

    /**
     * 设置监听事件
     */
    private void setClickListener(){
        patientLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断id pwd是否相等

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                user.setUserType("Patient");
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

            }
        });

        doctorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                Bundle bundle = new Bundle();
                user.setUserType("Doctor");
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

            }
        });

        patientRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                Bundle bundle = new Bundle();
                user.setUserType("Patient");
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

            }
        });

        doctorRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                Bundle bundle = new Bundle();
                user.setUserType("Doctor");
                bundle.putSerializable("user", user);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);

            }
        });
    }


    private class LoginViewPagerAdapter extends PagerAdapter{
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(loginViewPagerList.get(position));
            return loginViewPagerList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(loginViewPagerList.get(position));
        }

        @Override
        public int getCount() {
            return loginViewPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
