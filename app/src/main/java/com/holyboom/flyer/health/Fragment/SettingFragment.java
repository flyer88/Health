package com.holyboom.flyer.health.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.holyboom.flyer.health.Activity.LoginActivity;
import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Doctor;
import com.holyboom.flyer.health.model.Patient;
import com.holyboom.flyer.health.model.User;

/**
 * Created by flyer on 15/3/17.
 */
public class SettingFragment extends Fragment{

    Button exitButton;
    User user;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting,container,false);
        exitButton = (Button) view.findViewById(R.id.exit_doctor_button);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getUserInformation();
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor  editorLoginTime = getActivity().getSharedPreferences("LoginTime", Context.MODE_PRIVATE).edit();
                editorLoginTime.putInt("LoginTimes",0);
                editorLoginTime.commit();
                SharedPreferences.Editor userInfo = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE).edit();
                userInfo.putString("UserName", null);
                userInfo.putString("UserPwd", null);
                userInfo.commit();

                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    public void getUserInformation(){
        if( getActivity().getIntent().getSerializableExtra("user").getClass().equals(Patient.class)){
            user = new Patient();
            user = (Patient) getActivity().getIntent().getSerializableExtra("user");
        }else {
            user = new Doctor();
            user = (Doctor) getActivity().getIntent().getSerializableExtra("user");
        }
    }
}
