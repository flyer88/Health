package com.holyboom.flyer.health.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.holyboom.flyer.health.Activity.DoctorInfoActivity;
import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Doctor;
import com.holyboom.flyer.health.uitil.Delay;
import com.holyboom.flyer.health.uitil.DelayDelegate;
import com.holyboom.flyer.health.uitil.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/3/17.
 */
public class AskFragment extends Fragment{

    View view;
    List<Doctor> doctorList = new ArrayList<Doctor>();
    RecyclerView doctorListRecyclerView;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载progressbar；
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_ask,container,false);

        doctorListRecyclerView = (RecyclerView) view.findViewById(R.id.ask_fragment_doctor_list);
        progressBar = (ProgressBar) view.findViewById(R.id.ask_fragment_progress_bar);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        for (int i = 0;i<=20;i++) {
            doctorList.add(new Doctor("医生"+i+"号"));
        }
        doctorListRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        doctorListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        doctorListRecyclerView.setHasFixedSize(true);

        progressBar.setVisibility(View.VISIBLE);
        new Delay(1000,new DelayDelegate(){
            public void didDelay(){
                progressBar.setVisibility(View.INVISIBLE);
                doctorListRecyclerView.setAdapter(new DoctorListAdapter(doctorList));
            }
        }).delay();


        doctorListRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), doctorListRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(),DoctorInfoActivity.class);
                bundle.putSerializable("user", doctorList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        }
        ));
    }

    public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.ViewHolder>{

        List<Doctor> doctorList;

        public DoctorListAdapter(List<Doctor> doctorList){
            this.doctorList = doctorList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_ask, parent, false);
            return new ViewHolder(v);
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Doctor doctor =doctorList.get(position);
            holder.doctorID.setText(doctor.getId());
        }

        @Override
        public int getItemCount() {
            return doctorList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView doctorID;
            public ViewHolder(View itemView) {
                super(itemView);
                doctorID = (TextView) itemView.findViewById(R.id.ask_fragment_doctor_information);
            }
        }
    }
}
