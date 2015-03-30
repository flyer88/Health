package com.holyboom.flyer.health.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Request;

/**
 * Created by flyer on 15/3/20.
 */


public class PatientRequestActivity extends ActionBarActivity{

    TextView requestName,requestContent;
    Button agree,reject;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_request);

        Request request = (Request)getIntent().getSerializableExtra("PatientRequest");
        requestName = (TextView) findViewById(R.id.patient_request_name);
        requestContent = (TextView) findViewById(R.id.patient_request_content);
        agree = (Button) findViewById(R.id.patient_request_agree);
        reject = (Button) findViewById(R.id.patient_request_reject);
        requestName.setText(request.getTitle());
        requestContent.setText(request.getContent());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("病人请求");
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
