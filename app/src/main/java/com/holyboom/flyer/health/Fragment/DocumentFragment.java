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
import com.holyboom.flyer.health.Activity.AddDocumentActivity;
import com.holyboom.flyer.health.Activity.AddMettingActivity;
import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.PatientDocument;
import com.holyboom.flyer.health.uitil.Delay;
import com.holyboom.flyer.health.uitil.DelayDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/3/17.
 */
public class DocumentFragment extends Fragment{


    RecyclerView patientDocuemetRecyclerView;

    List<PatientDocument> patientDocumentList = new ArrayList<PatientDocument>();

    ProgressBar progressBar;
    PatientDocumentAdapter patientDocumentAdapter;

    FloatingActionButton shareDocument,addDocument;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_document,container,false);

        patientDocuemetRecyclerView = (RecyclerView) view.findViewById(R.id.patient_document_recycler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.document_fragment_progress_bar);

        addDocument = (FloatingActionButton) view.findViewById(R.id.document_add);
        shareDocument = (FloatingActionButton) view.findViewById(R.id.document_share);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //加载progressbar
//        for(int i =10 ;i<=23;i++){
        patientDocumentList.add(new PatientDocument("2月"+10+"号","严重流行性感冒"));
        patientDocumentList.add(new PatientDocument("2月"+11+"号","感冒导致发烧"));
        patientDocumentList.add(new PatientDocument("2月"+12+"号","吊瓶后烧退"));
        patientDocumentList.add(new PatientDocument("2月"+13+"号","烧退，感冒开始消退"));
        patientDocumentList.add(new PatientDocument("2月"+14+"号","吃白加黑，感冒基本已经消失"));
        patientDocumentList.add(new PatientDocument("2月"+15+"号","病好"));

//        }
        patientDocumentAdapter = new PatientDocumentAdapter(patientDocumentList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        patientDocuemetRecyclerView.setLayoutManager(linearLayoutManager);
        patientDocuemetRecyclerView.setItemAnimator(new DefaultItemAnimator());
        patientDocuemetRecyclerView.setHasFixedSize(true);

        progressBar.setVisibility(View.VISIBLE);
        new Delay(1000,new DelayDelegate(){
            public void didDelay(){
                progressBar.setVisibility(View.INVISIBLE);
                patientDocuemetRecyclerView.setAdapter(patientDocumentAdapter);
            }
        }).delay();



        addDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDocumentActivity.class);
                startActivityForResult(intent,5);
            }
        });

        shareDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                intent.putExtra(Intent.EXTRA_TEXT, "我病好了！！");

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent,"什么鬼？"));
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 5:
                PatientDocument patientDocument = (PatientDocument) data.getSerializableExtra("update_patient_document_data_return");
                patientDocumentList.add(patientDocument);
                patientDocumentAdapter = new PatientDocumentAdapter(patientDocumentList);
                patientDocuemetRecyclerView.setAdapter(patientDocumentAdapter);
                break;
            default:
        }
    }

    /**
     *  重写recyclerview的adapter
     */
    public class PatientDocumentAdapter extends RecyclerView.Adapter<PatientDocumentAdapter.ViewHolder>{

        List<PatientDocument> patientDocumentList;

        public PatientDocumentAdapter(List<PatientDocument> patientDocumentList){
            this.patientDocumentList = patientDocumentList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_document, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            PatientDocument p = patientDocumentList.get(position);
            holder.time.setText(p.getIllnessTime());
            holder.content.setText(p.getIllnessContent());
        }

        @Override
        public int getItemCount() {
            return patientDocumentList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView time,content;
            public ViewHolder(View itemView) {
                super(itemView);
                time = (TextView) itemView.findViewById(R.id.document_fragment_time);
                content = (TextView) itemView.findViewById(R.id.document_fragment_content);
            }
        }
    }
}
