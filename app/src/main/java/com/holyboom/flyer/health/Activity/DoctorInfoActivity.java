package com.holyboom.flyer.health.Activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Doctor;
import com.holyboom.flyer.health.model.DoctorInformation;

/**
 * Created by flyer on 15/3/23.
 */
public class DoctorInfoActivity extends ActionBarActivity{
    FragmentTransaction transaction;

    private TextView name,year,filed,identification,hospital;
    Toolbar toolbar;
    Button choose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_information);
        Doctor doctor = (Doctor)getIntent().getSerializableExtra("user");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("选择医生");
        choose = (Button) findViewById(R.id.choose_doctor);
        name = (TextView) findViewById(R.id.head_fragment_doctor_name);
        year = (TextView) findViewById(R.id.head_fragment_doctor_year);
        filed = (TextView) findViewById(R.id.head_fragment_doctor_filed);
        identification = (TextView) findViewById(R.id.head_fragment_doctor_identification);
        hospital = (TextView) findViewById(R.id.head_fragment_doctor_hospital);
        DoctorInformation doctorInformation = new DoctorInformation(doctor.getId(),"40","脑科","浙江人民医院","是");
        name.setText(doctorInformation.getName()+"");
        year.setText(doctorInformation.getYear()+"");
        filed.setText(doctorInformation.getFiled()+"");
        identification.setText(doctorInformation.getIdentification()+"");
        hospital.setText(doctorInformation.getHospital()+"");
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
