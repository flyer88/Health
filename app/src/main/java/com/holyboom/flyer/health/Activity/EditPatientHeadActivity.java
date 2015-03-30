package com.holyboom.flyer.health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.PatientInformation;

/**
 * Created by flyer on 15/3/24.
 */
public class EditPatientHeadActivity extends ActionBarActivity{

    TextView name,year,sex;
    Button patientActivityEdit;
    PatientInformation patientInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_head_edit);
        name = (TextView) findViewById(R.id.activity_patient_name);
        year = (TextView) findViewById(R.id.activity_patient_year);
        sex = (TextView) findViewById(R.id.activity_patient_sex);
        patientActivityEdit = (Button) findViewById(R.id.patient_activity_head_edit);

        patientActivityEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String patientName = name.getText().toString();
                String patientYear = year.getText().toString();
                String patientSex = sex.getText().toString();

                patientInformation =new PatientInformation(patientName,patientYear,patientSex);

                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("update_patient_information_data_return",patientInformation);
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
