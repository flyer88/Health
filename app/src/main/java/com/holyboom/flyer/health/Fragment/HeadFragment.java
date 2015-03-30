package com.holyboom.flyer.health.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.holyboom.flyer.health.Activity.EditDoctorHeadActivity;
import com.holyboom.flyer.health.Activity.EditPatientHeadActivity;
import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Doctor;
import com.holyboom.flyer.health.model.DoctorInformation;
import com.holyboom.flyer.health.model.Patient;
import com.holyboom.flyer.health.model.PatientInformation;
import com.holyboom.flyer.health.model.User;
import com.holyboom.flyer.health.uitil.Delay;
import com.holyboom.flyer.health.uitil.DelayDelegate;

/**
 * Created by flyer on 15/3/17.
 */
public class HeadFragment extends Fragment{

    DoctorInformation doctorInformation;
    PatientInformation patientInformation;
    User user;

    private TextView name,year,filed,identification,hospital,sex;
    ProgressBar progressBar;
    Button editInformation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Log.e("HeadFragment 用户类型", getActivity().getIntent().getSerializableExtra("user").getClass() + "");
        if ((getActivity().getIntent().getSerializableExtra("user").getClass()).equals(Patient.class)) {


            View view = inflater.inflate(R.layout.fragment_patient_head,container,false);

            user = new Patient();
            user = (Patient) getActivity().getIntent().getSerializableExtra("user");
            patientInformation = new PatientInformation("患者1号","40","男");
            name = (TextView) view.findViewById(R.id.head_fragment_patient_name);
            year = (TextView) view.findViewById(R.id.head_fragment_patient_year);
            sex = (TextView) view.findViewById(R.id.head_fragment_patient_sex);
            progressBar = (ProgressBar) view.findViewById(R.id.patient_head_fragment_progress_bar);
            editInformation = (Button) view.findViewById(R.id.patient_head_fragment_edit);

            progressBar.setVisibility(View.VISIBLE);
            new Delay(1000,new DelayDelegate(){
                public void didDelay(){
                    progressBar.setVisibility(View.INVISIBLE);
                    name.setText(patientInformation.getName());
                    year.setText(patientInformation.getYear()+"");
                    sex.setText(patientInformation.getSex()+"");
                }
            }).delay();

            editInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), EditPatientHeadActivity.class);
                    startActivityForResult(intent,3);
                }
            });

            return view;
        }else {
            View view = inflater.inflate(R.layout.fragment_doctor_head,container,false);
            user = new Doctor();
            user = (Doctor) getActivity().getIntent().getSerializableExtra("user");

            doctorInformation = new DoctorInformation("医生1号", "10", "三高，骨科", "浙江人民医院", "是");
            name = (TextView) view.findViewById(R.id.head_fragment_doctor_name);
            year = (TextView) view.findViewById(R.id.head_fragment_doctor_year);
            filed = (TextView) view.findViewById(R.id.head_fragment_doctor_filed);
            identification = (TextView) view.findViewById(R.id.head_fragment_doctor_identification);
            hospital = (TextView) view.findViewById(R.id.head_fragment_doctor_hospital);
            progressBar = (ProgressBar) view.findViewById(R.id.doctor_head_fragment_progress_bar);
            editInformation = (Button) view.findViewById(R.id.doctor_head_fragment_edit);

            progressBar.setVisibility(View.VISIBLE);
            new Delay(1000,new DelayDelegate(){
                public void didDelay(){
                    progressBar.setVisibility(View.INVISIBLE);
                    name.setText(doctorInformation.getName());
                    year.setText(doctorInformation.getYear() + "");
                    filed.setText(doctorInformation.getFiled() + "");
                    identification.setText(doctorInformation.getIdentification() + "");
                    hospital.setText(doctorInformation.getHospital() + "");
                }
            }).delay();

            editInformation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), EditDoctorHeadActivity.class);
                    startActivityForResult(intent,4);
                }
            });

            return view;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 3:
                if (resultCode == getActivity().RESULT_OK){
                    PatientInformation patientInformation1 = (PatientInformation) data.getSerializableExtra("update_patient_information_data_return");
                    name.setText(patientInformation1.getName());
                    year.setText(patientInformation1.getYear()+"");
                    sex.setText(patientInformation1.getSex()+"");
                }
                break;
            case 4:
                if (resultCode == getActivity().RESULT_OK){
                    DoctorInformation doctorInformation1 = (DoctorInformation) data.getSerializableExtra("update_doctor_information_data_return");
                    name.setText(doctorInformation1.getName());
                    year.setText(doctorInformation1.getYear() + "");
                    filed.setText(doctorInformation1.getFiled() + "");
                    identification.setText(doctorInformation1.getIdentification() + "");
                    hospital.setText(doctorInformation1.getHospital() + "");
                }
                break;
            default:
        }
    }
}
