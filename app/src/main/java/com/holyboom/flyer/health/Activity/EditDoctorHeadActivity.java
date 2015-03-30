package com.holyboom.flyer.health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.DoctorInformation;

/**
 * Created by flyer on 15/3/24.
 */
public class EditDoctorHeadActivity extends ActionBarActivity{

    TextView name,year,filed,hospital,identification;
    Button doctorActivityEdit;
    DoctorInformation doctorInformation ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_head_edit);

        name = (TextView) findViewById(R.id.activity_doctor_name);
        year = (TextView) findViewById(R.id.activity_doctor_year);
        filed = (TextView) findViewById(R.id.activity_doctor_filed);
        hospital = (TextView) findViewById(R.id.activity_doctor_hospital);
        identification = (TextView) findViewById(R.id.activity_doctor_identification);

        doctorActivityEdit = (Button) findViewById(R.id.doctor_activity_head_edit);
        doctorActivityEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctorName = name.getText().toString();
                String doctorYear = year.getText().toString();
                String doctorFiled = filed.getText().toString();
                String doctorHospital = hospital.getText().toString();
                String doctorIdentification = identification.getText().toString();

                doctorInformation = new DoctorInformation(doctorName,doctorYear,doctorFiled,doctorHospital,doctorIdentification);

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("update_doctor_information_data_return",doctorInformation);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
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
