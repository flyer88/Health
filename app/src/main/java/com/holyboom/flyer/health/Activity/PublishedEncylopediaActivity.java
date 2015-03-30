package com.holyboom.flyer.health.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.EncylopediaInformation;

/**
 * Created by flyer on 15/3/20.
 */
public class PublishedEncylopediaActivity extends ActionBarActivity{
    TextView title,content;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encylopedia_published);
        EncylopediaInformation encylopediaInformation = (EncylopediaInformation) PublishedEncylopediaActivity.this.getIntent().getSerializableExtra("EncylopediaInformation");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("百科-"+encylopediaInformation.getTitle());

        title = (TextView) findViewById(R.id.encylopedia_published_title);
        content = (TextView) findViewById(R.id.encylopedia_published_content);
        title.setText(encylopediaInformation.getTitle()+"");
        content.setText(encylopediaInformation.getContent()+"");
    }
}
