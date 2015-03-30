package com.holyboom.flyer.health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.EncylopediaInformation;

/**
 * Created by flyer on 15/3/20.
 */
public class EditEncylopediaActivity extends ActionBarActivity{

    TextView title;
    TextView content;
    Button publish;
    Toolbar toolbar;
    EncylopediaInformation encylopediaInformation = new EncylopediaInformation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encylopedia_edit);
        title = (TextView) findViewById(R.id.encylopedia_edit_title);
        content = (TextView) findViewById(R.id.encylopedia_edit_content);
        publish = (Button) findViewById(R.id.encylopedia_edit_publish);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("百科-编辑");

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encylopediaInformation.setTitle(title.getText().toString());
                encylopediaInformation.setContent(content.getText().toString());
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("add_encylopedia_data_return", encylopediaInformation);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }
}
