package com.holyboom.flyer.health.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.holyboom.flyer.health.Fragment.AskFragment;
import com.holyboom.flyer.health.Fragment.DocumentFragment;
import com.holyboom.flyer.health.Fragment.EncyclopediaFragment;
import com.holyboom.flyer.health.Fragment.HeadFragment;
import com.holyboom.flyer.health.Fragment.MettingFragment;
import com.holyboom.flyer.health.Fragment.NotificationFragment;
import com.holyboom.flyer.health.Fragment.SettingFragment;
import com.holyboom.flyer.health.HealthApp;
import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Doctor;
import com.holyboom.flyer.health.model.Patient;
import com.holyboom.flyer.health.model.User;


public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    DrawerLayout drawerNavigation;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ListView navigationListView;
    String[] navigationList;
    ArrayAdapter arrayAdapter;
    LinearLayout navigationBar;
    User user;
    ImageButton head;
    FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //登录成功 改变FirstTIME_LOGIN的值
        HealthApp.FIRST_TIME_LOGIN +=1;
        saveLoginTime();
        getUserInformation();
        initToolbar();
        initFragment();
        initDrawerNavigation();
        head = (ImageButton) findViewById(R.id.head);
        head.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getSupportActionBar().setTitle("个人信息");
                FragmentManager fragmentManager = getFragmentManager();
                Fragment headFragment = new HeadFragment();
                transaction = fragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                headFragment.setArguments(bundle);
                transaction.replace(R.id.main_content_frame, headFragment).commit();
                drawerNavigation.closeDrawer(navigationBar);
            }
        });
    }

    /**
     * 获取用户登录类型
     */
    public void getUserInformation(){
        if( getIntent().getSerializableExtra("user").getClass().equals(Patient.class)){
            user = new Patient();
            user = (Patient) getIntent().getSerializableExtra("user");
        }else {
            user = new Doctor();
            user = (Doctor) getIntent().getSerializableExtra("user");
        }
    }

    /**
     * 保存登录次数到本地
     */
    public void saveLoginTime(){
        SharedPreferences.Editor editor = getSharedPreferences("LoginTime",MODE_PRIVATE).edit();
        editor.putInt("LoginTimes", HealthApp.FIRST_TIME_LOGIN);
        editor.commit();
    }
    /**
     * 初始化toolbar
     */
    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerNavigation = (DrawerLayout)findViewById(R.id.drawer_navigation);
        navigationListView = (ListView) findViewById(R.id.navigation_list_view);
        navigationBar = (LinearLayout) findViewById(R.id.navigation_bar);
        //toolbar.setTitle("Toolbar");//设置Toolbar标题
        setSupportActionBar(toolbar);
        //toolbar.setTitle("Toolbar");//设置Toolbar标题
//        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//       getSupportActionBar().setTitle("标题");

    }

    public void initFragment(){
        if(user.getClass().equals(Doctor.class)) {
            getSupportActionBar().setTitle("通知");
            FragmentManager fragmentManager = getFragmentManager();
            Fragment notificationFragment = new NotificationFragment();
            fragmentManager.beginTransaction().replace(R.id.main_content_frame, notificationFragment).commit();
            drawerNavigation.closeDrawer(navigationBar);
        }else{
            getSupportActionBar().setTitle("医疗档案");
            FragmentManager fragmentManager = getFragmentManager();
            Fragment documentFragment = new DocumentFragment();
            fragmentManager.beginTransaction().replace(R.id.main_content_frame, documentFragment).commit();
            drawerNavigation.closeDrawer(navigationBar);
        }

    }

    /**
     * 初始化navigation
     */
    public void initDrawerNavigation(){
        actionBarDrawerToggle =  new ActionBarDrawerToggle(MainActivity.this, drawerNavigation, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }
        };
        actionBarDrawerToggle.syncState();
        drawerNavigation.setDrawerListener(actionBarDrawerToggle);
        navigationList = user.getNavigationList();
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,navigationList);
        navigationListView.setAdapter(arrayAdapter);
        if (user.getClass().equals(Patient.class)) {
            navigationListView.setOnItemClickListener(new PatientDrawerItemClickListener());
        }else {
            navigationListView.setOnItemClickListener(new DoctorDrawerItemClickListener());

        }

    }

    /**
     * patient的侧滑栏的点击事件
     */
    public class PatientDrawerItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentManager fragmentManager = getFragmentManager();
            switch (position){
                case 0:
                    getSupportActionBar().setTitle("医疗档案");
                    Fragment documentFragment = new DocumentFragment();
                    fragmentManager.beginTransaction().replace(R.id.main_content_frame,documentFragment).commit();
                    drawerNavigation.closeDrawer(navigationBar);
                    break;
                case 1:
                    getSupportActionBar().setTitle("医生列表");
                    Fragment askFragment = new AskFragment();
                    fragmentManager.beginTransaction().replace(R.id.main_content_frame,askFragment).commit();
                    drawerNavigation.closeDrawer(navigationBar);
                    break;
                case 2:
                    getSupportActionBar().setTitle("关于");
                    Fragment settingFragment = new SettingFragment();
                    fragmentManager.beginTransaction().replace(R.id.main_content_frame,settingFragment).commit();
                    drawerNavigation.closeDrawer(navigationBar);
                    break;
                default:
                    drawerNavigation.closeDrawer(navigationBar);
                    break;

            }

        }
    }

    /**
     * doctor的侧滑栏点击事件
     */
    public class DoctorDrawerItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FragmentManager fragmentManager = getFragmentManager();
            switch (position){
                case 0:
                    getSupportActionBar().setTitle("通知");

                    Fragment notificationFragment = new NotificationFragment();
                    fragmentManager.beginTransaction().replace(R.id.main_content_frame,notificationFragment).commit();
                    drawerNavigation.closeDrawer(navigationBar);
                    break;
                case 1:
                    getSupportActionBar().setTitle("百科");

                    Fragment encyclopediaFragment = new EncyclopediaFragment();
                    fragmentManager.beginTransaction().replace(R.id.main_content_frame,encyclopediaFragment).commit();
                    drawerNavigation.closeDrawer(navigationBar);
                    break;
                case 2:
                    getSupportActionBar().setTitle("见面会");

                    Fragment mettingFragment = new MettingFragment();
                    fragmentManager.beginTransaction().replace(R.id.main_content_frame,mettingFragment).commit();
                    drawerNavigation.closeDrawer(navigationBar);
                    break;
                case 3:
                    getSupportActionBar().setTitle("关于");

                    Fragment settingFragment = new SettingFragment();
                    fragmentManager.beginTransaction().replace(R.id.main_content_frame,settingFragment).commit();
                    drawerNavigation.closeDrawer(navigationBar);
                    break;
                default:
                    break;

            }
        }
    }

}


