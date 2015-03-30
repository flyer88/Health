package com.holyboom.flyer.health.Activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Doctor;
import com.holyboom.flyer.health.model.Patient;
import com.holyboom.flyer.health.model.User;
import com.holyboom.flyer.health.uitil.Delay;
import com.holyboom.flyer.health.uitil.DelayDelegate;

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
    User user = new Patient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        if (getLoginTime() == 0) {
            setClickListener();
        }else{
            ReadUserFromLocal();
            String userID;
            String userPwd;
            if (user.getClass().equals(Patient.class)){
                userID = "patient";
                userPwd = "patient";
            }else {
                userID = "doctor";
                userPwd = "doctor";
            }
            if ((user.getId().equals(userID)) && (user.getPwd().equals(userPwd))) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                putUserInformationToBundle(intent);
            }else {
                Toast.makeText(LoginActivity.this,"错误的账户或密码",Toast.LENGTH_SHORT).show();
                user.setId(patientUserId.getText().toString());
                user.setPwd(patientUserPwd.getText().toString());
                setClickListener();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.finish();
    }

    /**
     * 获取登录次数
     * 如果登录次数不为0
     * 设置FIRST_TIME_LOGIN = 1
     */
    public int getLoginTime(){
        SharedPreferences preferences = getSharedPreferences("LoginTime",MODE_PRIVATE);
        int time = preferences.getInt("LoginTimes",0);
        return time;
    }

    /**
     * 初始化UI
     */
    private void initUI(){
        loginViewPager = (ViewPager) findViewById(R.id.login_view_pager);
        LayoutInflater layoutInflater = getLayoutInflater().from(this);
        View patientLoginItem = layoutInflater.inflate(R.layout.item_login_patient,null);
        View doctorLoginItem = layoutInflater.inflate(R.layout.item_login_doctor,null);
        loginViewPagerList.add(patientLoginItem);
        loginViewPagerList.add(doctorLoginItem);
        patientLogin = (Button) patientLoginItem.findViewById(R.id.login_patient_button);
        patientRegister = (Button) patientLoginItem.findViewById(R.id.to_register_patient_button);
        doctorLogin = (Button) doctorLoginItem.findViewById(R.id.login_doctor_button);
        doctorRegister = (Button) doctorLoginItem.findViewById(R.id.to_register_doctor_button);
        patientUserId = (EditText) patientLoginItem.findViewById(R.id.patient_user_id);
        patientUserPwd = (EditText) patientLoginItem.findViewById(R.id.patient_user_pwd);
        doctorUserId = (EditText) doctorLoginItem.findViewById(R.id.doctor_user_id);
        doctorUserPwd = (EditText) doctorLoginItem.findViewById(R.id.doctor_user_pwd);


        LoginViewPagerAdapter loginViewPagerAdapter = new LoginViewPagerAdapter();
        loginViewPager.setAdapter(loginViewPagerAdapter);
        loginViewPager.setOnPageChangeListener(new LoginViewPagerListener());
    }

    /**
     * 设置监听事件
     */
    private void setClickListener(){
        patientLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断id pwd是否相等
                //验证用户登录
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setTitle("病人");
                progressDialog.setMessage("登陆中....");
                progressDialog.setCancelable(false);
                progressDialog.show();
                user.setId(patientUserId.getText().toString());
                user.setPwd(patientUserPwd.getText().toString());
                if ((user.getId().equals("patient")) && (user.getPwd().equals("patient"))) {
                    //Log.e("用户账号", user.getId());
                    new Delay(3000,new DelayDelegate(){
                        public void didDelay(){
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            putUserInformationToBundle(intent);
                            saveUserToLocal();
                        }
                    }).delay();
                }else if ((user.getId() == null) || (user.getPwd() == null)){
                    new Delay(3000,new DelayDelegate(){
                        public void didDelay(){
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            putUserInformationToBundle(intent);
                            saveUserToLocal();
                        }
                    }).delay();
                    Toast.makeText(LoginActivity.this,"请输入账户和密码",Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this,"错误的账户或密码",Toast.LENGTH_SHORT).show();
                }
            }
        });

        doctorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断id pwd是否相等
                //验证用户登录
                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setTitle("医生");
                progressDialog.setMessage("登陆中....");
                progressDialog.setCancelable(false);
                progressDialog.show();
                user.setId(doctorUserId.getText().toString());
                user.setPwd(doctorUserPwd.getText().toString());
                if ((user.getId().equals("doctor")) && (user.getPwd().equals("doctor"))) {
                    //Log.e("用户账号", user.getId());
                    //progressbar
                    new Delay(3000,new DelayDelegate(){
                        public void didDelay(){
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            putUserInformationToBundle(intent);
                            saveUserToLocal();
                        }
                    }).delay();
                }else if ((user.getId() == null) || (user.getPwd() == null)){
                    new Delay(3000,new DelayDelegate(){
                        public void didDelay(){
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            putUserInformationToBundle(intent);
                            saveUserToLocal();
                        }
                    }).delay();
                    Toast.makeText(LoginActivity.this,"请输入账户和密码",Toast.LENGTH_SHORT).show();
                }else {
                    new Delay(3000,new DelayDelegate(){
                        public void didDelay(){
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            putUserInformationToBundle(intent);
                            saveUserToLocal();
                        }
                    }).delay();
                    Toast.makeText(LoginActivity.this,"错误的账户或密码",Toast.LENGTH_SHORT).show();
                }
            }
        });

        patientRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((patientUserId.getText() != null) || (patientUserPwd.getText() != null)) {
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setTitle("医生");
                    progressDialog.setMessage("注册登陆中....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    user.setId(patientUserId.getText().toString());
                    user.setPwd(patientUserPwd.getText().toString());
                    saveUserToLocal();
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notification = new Notification(R.drawable.logo, "注册完成", System.currentTimeMillis());
                    notification.setLatestEventInfo(LoginActivity.this, "注册完成,正在登陆", "账户注册完成", null);
                    notificationManager.notify(1, notification);
                    //progressbar
                    new Delay(3000,new DelayDelegate(){
                        public void didDelay(){
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            putUserInformationToBundle(intent);
                            saveUserToLocal();
                        }
                    }).delay();
                } else {
                    Toast.makeText(LoginActivity.this, "请输入完整的用户名和密码", Toast.LENGTH_SHORT).show();
                }
            }
        });

        doctorRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((doctorUserId.getText().toString() != null) && (doctorUserPwd.getText().toString() !=null)) {
                    //Log.e("LoginActivity 用户账号",doctorUserId.getText().toString());
                    final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                    progressDialog.setTitle("医生");
                    progressDialog.setMessage("注册登陆中....");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    user.setId(doctorUserId.getText().toString());
                    user.setPwd(doctorUserPwd.getText().toString());
                    saveUserToLocal();
                    NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    Notification notification = new Notification(R.drawable.logo, "注册完成", System.currentTimeMillis());
                    notification.setLatestEventInfo(LoginActivity.this, "注册完成,正在登陆", "账户注册完成", null);
                    notificationManager.notify(1, notification);
                    //progressbar
                    new Delay(3000,new DelayDelegate(){
                        public void didDelay(){
                            progressDialog.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            putUserInformationToBundle(intent);
                            saveUserToLocal();
                        }
                    }).delay();
                }else{
                    Toast.makeText(LoginActivity.this,"请输入完整的用户名和密码",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 存储数据到Bundle
     * @param intent
     */
    private void putUserInformationToBundle(Intent intent){
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }


    /**
     * 保存用户账号密码到本地
     */
    private void saveUserToLocal(){
        if (user.getClass().equals(Patient.class)) {
            //Log.e("LoginActivity 用户类型:",user.getClass()+"");
            SharedPreferences.Editor editor = getSharedPreferences("UserInfo", MODE_PRIVATE).edit();
            //Log.e("LoginActivity 病人账户：",patientUserId.getText().toString());
            editor.putString("UserType","Patient");
            editor.putString("UserName", user.getId());
            editor.putString("UserPwd", user.getPwd());
            editor.commit();
        }else {
           // Log.e("LoginActivity 用户类型:",user.getClass()+"");
            SharedPreferences.Editor editor = getSharedPreferences("UserInfo",MODE_PRIVATE).edit();
            editor.putString("UserType","Doctor");
            editor.putString("UserName", user.getId());
            editor.putString("UserPwd", user.getPwd());
            editor.commit();
        }
    }

    /**
     * 本地验证用户密码账号
     * 同事验证账户类型
     */
    private void ReadUserFromLocal(){

        //Log.e("LoginActivity 用户类型:",user.getClass()+"");
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String userType = sharedPreferences.getString("UserType","");
        if (userType.equals("Patient")) {
            user = new Patient();
        }else {
            user = new Doctor();
        }
        user.setId(sharedPreferences.getString("UserName", ""));
        user.setPwd(sharedPreferences.getString("UserPwd", ""));
    }

    /**
     * 滑动切换医生和患者的登录
     */
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

    /**
     * listener 用来监听处于病人还是医生界面，从而初始化user
     */
    private class LoginViewPagerListener implements ViewPager.OnPageChangeListener{

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    user = new Patient();
                    //Log.e("LoginActivity 用户类型:",user.getClass()+"");
                    break;
                case 1:
                    user = new Doctor();
                    //Log.e("LoginActivity 用户类型:",user.getClass()+"");
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
    /**
     * 网络验证账户id和mim
     */
    private boolean checkUserWithHttp(){
        if (user.getId().equals("18667009152") && user.getPwd().equals("flyer123")){
            //加载progressbar;
            return true;
        }else {
            return false;
        }
    }
}
