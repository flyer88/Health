package com.holyboom.flyer.health.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.holyboom.flyer.health.Fragment.HeadFragment;
import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.User;


public class MainActivity extends ActionBarActivity {

    Toolbar toolbar;
    DrawerLayout drawerNavigation;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ListView navigationListView;
    String[] navigationList;
    ArrayAdapter arrayAdapter;
    LinearLayout navigationBar,mainLayout;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserImformation();
        initToolbar();
        initDrawerNavigation();


    }

    public void getUserImformation(){
        user = (User) getIntent().getSerializableExtra("user");
    }


    /**
     * 初始化toolbar
     */
    public void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerNavigation = (DrawerLayout)findViewById(R.id.drawer_navigation);
        navigationListView = (ListView) findViewById(R.id.navigation_list_view);
        navigationBar = (LinearLayout) findViewById(R.id.navigation_bar);
        toolbar.setTitle("Toolbar");//设置Toolbar标题
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    /**
     * 更具不同Extra加载不同的navigation的String
     */
    public void getNavigationAdapter(){
        Intent intent = this.getIntent();
        String userType = intent.getStringExtra("UserType");
        if (userType.equals("Patient")){
            navigationList = new String[]{"个人资料","医疗档案","求医","软件设置"};
        }else{
            navigationList = new String[]{"个人资料","通知","百科","见面会","软件设置"};
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
        //getNavigationAdapter();
        if (user.getUserType().equals("Patient")){
            navigationList = new String[]{"个人资料","医疗档案","求医","软件设置"};
        }else if (user.getUserType().equals("Doctor")){
            navigationList = new String[]{"个人资料","通知","百科","见面会","软件设置"};
        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, navigationList);
        navigationListView.setAdapter(arrayAdapter);
        navigationListView.setOnItemClickListener(new DrawerItemClickListener());
    }

    /**
     * navigationgbar的item点击时间
     */
    public class DrawerItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Fragment fragment = new HeadFragment();
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content_frame,fragment).commit();
            drawerNavigation.closeDrawer(navigationBar);
        }
    }
}
