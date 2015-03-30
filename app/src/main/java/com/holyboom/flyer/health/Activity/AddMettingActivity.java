package com.holyboom.flyer.health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Metting;
import com.holyboom.flyer.health.model.Patient;

/**
 * Created by flyer on 15/3/24.
 */
public class AddMettingActivity extends ActionBarActivity{


    TextView addMettingTime,addMettingPlace,addMettingPatient;
    Button sure;
    Metting metting = new Metting();
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metting_add);
        addMettingTime = (TextView) findViewById(R.id.add_metting_time);
        addMettingPatient = (TextView) findViewById(R.id.add_metting_patient);
        addMettingPlace = (TextView) findViewById(R.id.add_metting_place);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("添加见面会");
//        String time = addMettingTime.getText().toString();
//
//        Log.e("addMetting 时间",time);

        sure = (Button) findViewById(R.id.add_metting_sure);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metting.setTime(addMettingTime.getText()+"");
                metting.setPlace(addMettingPlace.getText() + "");
                metting.setPatient(new Patient(addMettingPatient.getText()+""));
                Log.e("addMetting 时间",addMettingTime.getText().toString());
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("add_metting_data_return", metting);
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
