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

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.holyboom.flyer.health.Activity.AddMettingActivity;
import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Metting;
import com.holyboom.flyer.health.model.Patient;
import com.holyboom.flyer.health.uitil.Delay;
import com.holyboom.flyer.health.uitil.DelayDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/3/17.
 */
public class MettingFragment extends Fragment {

    RecyclerView mettingRecyclerView;
    List<Metting> mettingList = new ArrayList<Metting>();

    MettingAdapter mettingAdapter;
    ProgressBar progressBar;
    FloatingActionButton addMetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_metting,container,false);

        initUI(view);
        return view;
    }

    private void initUI(View view){
        mettingRecyclerView = (RecyclerView) view.findViewById(R.id.metting_fragment_recyler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.metting_fragment_progress_bar);
        addMetting = (FloatingActionButton) view.findViewById(R.id.add_metting);
    }

    private void initRecyclerViewAdapter(){
        mettingList.add(new Metting("206", "3月10号", new Patient("罗永浩")));
        mettingList.add(new Metting("303","3月15号",new Patient("雷军")));
        mettingList.add(new Metting("206", "3月10号", new Patient("刘翔")));
        mettingList.add(new Metting("303","3月15号",new Patient("乔布斯")));
        mettingList.add(new Metting("206", "3月10号", new Patient("比尔盖茨")));
        mettingList.add(new Metting("303","3月15号",new Patient("轮子哥")));
        mettingList.add(new Metting("206", "3月10号", new Patient("winter")));
        mettingList.add(new Metting("303","3月15号",new Patient("saber")));
        mettingRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mettingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mettingRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerViewAdapter();
        mettingAdapter = new MettingAdapter(mettingList);
        progressBar.setVisibility(View.VISIBLE);
        new Delay(1000,new DelayDelegate(){
            public void didDelay(){
                progressBar.setVisibility(View.INVISIBLE);
                mettingRecyclerView.setAdapter(mettingAdapter);
            }
        }).delay();

        addMetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddMettingActivity.class);
                startActivityForResult(intent,1);
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == getActivity().RESULT_OK){

                    Metting metting = (Metting) data.getSerializableExtra("add_metting_data_return");
                    //Log.e("MettingFragment 返还数据", metting.getTime());
                    mettingList.add(metting);
                    mettingAdapter = new MettingAdapter(mettingList);
                    mettingRecyclerView.setAdapter(mettingAdapter);
                }
                break;
            default:
        }
    }

    public class MettingAdapter extends RecyclerView.Adapter<MettingAdapter.ViewHolder>{

        List<Metting> mettingList;

        public MettingAdapter(List<Metting> mettingList){
            this.mettingList = mettingList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_metting, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Metting p = mettingList.get(position);
            holder.title.setText(p.getTime());
            holder.place.setText(p.getPlace());
            holder.patientID.setText(p.getPatient().getId());
        }

        @Override
        public int getItemCount() {
            return mettingList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView title,place,patientID;
            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.metting_fragment_time);
                place = (TextView) itemView.findViewById(R.id.metting_fragment_place);
                patientID = (TextView) itemView.findViewById(R.id.metting_fragment_patient_id);
            }
        }
    }

}
