package com.holyboom.flyer.health.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.holyboom.flyer.health.Activity.PatientQestionActivity;
import com.holyboom.flyer.health.Activity.PatientRequestActivity;
import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Patient;
import com.holyboom.flyer.health.model.Question;
import com.holyboom.flyer.health.model.Request;
import com.holyboom.flyer.health.uitil.Delay;
import com.holyboom.flyer.health.uitil.DelayDelegate;
import com.holyboom.flyer.health.uitil.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/3/17.
 */
public class NotificationFragment extends Fragment{

    ViewPager notificationViewPager;
    List<View> notificationViewPagerList = new ArrayList<View>() ;

    RecyclerView notificationRequestRecyclerView;
    List<Request> notificationRequestList = new ArrayList<Request>();
    RecyclerView notificationQuestionRecyclerView;
    List<Question> notificationQuestionList = new ArrayList<Question>();
    ImageButton requestButton,questionButton;
    NotificationQuestionAdapter questionAdapter;
    NotificationRequestAdapter requestAdapter;

    ProgressBar progressBarQuestion,progressBarRequest;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification,container,false);
        initUI(view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        for(int i =0 ;i<=4;i++){
//            notificationQuestionList.add(new Question(i + "时间", i + "神经病"));
//        }
        notificationQuestionList.add(new Question("3月10号","低血压患者应该多吃什么？？",new Patient("乔布斯")));
        notificationQuestionList.add(new Question("3月14号","糖尿病患者不应该吃什么？",new Patient("比尔盖茨")));
        notificationQuestionList.add(new Question("3月16号","高血脂患者不应该吃什么？",new Patient("雷军")));
        notificationQuestionList.add(new Question("3月22号","高血压患者不应该吃什么？",new Patient("罗永浩")));
        notificationQuestionList.add(new Question("3月24号","高血糖患者不应该吃什么？",new Patient("刘翔")));
        questionAdapter = new NotificationQuestionAdapter(notificationQuestionList);
        notificationQuestionRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        notificationQuestionRecyclerView.setItemAnimator(new DefaultItemAnimator());
        notificationQuestionRecyclerView.setHasFixedSize(true);

        progressBarQuestion.setVisibility(View.VISIBLE);
        new Delay(1000,new DelayDelegate(){
            public void didDelay(){
                progressBarQuestion.setVisibility(View.INVISIBLE);
                notificationQuestionRecyclerView.setAdapter(questionAdapter);            }
        }).delay();



        notificationQuestionRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(), notificationQuestionRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), PatientQestionActivity.class);
                bundle.putSerializable("PatientQuestion", notificationQuestionList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        }));


//        for(int i =0 ;i<=4;i++){
//            notificationRequestList.add(new Request(i + "时间", i + "神经病"));
//        }
        notificationRequestList.add(new Request("3月2号","您有情怀么？看看我的高血脂吧",new Patient("罗永浩")));
        notificationRequestList.add(new Request("3月8号","您周末有空么？低血糖越来越严重了",new Patient("乔布斯")));
        notificationRequestList.add(new Request("3月10号","糖尿病好了，周一去结账",new Patient("比尔盖茨")));
        notificationRequestList.add(new Request("3月12号","药吃完了，周三去拿药",new Patient("雷军")));
        notificationRequestList.add(new Request("3月17号","锤子砸的头好痛，周四去拿药",new Patient("刘翔")));

        requestAdapter = new NotificationRequestAdapter(notificationRequestList);
        notificationRequestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        notificationRequestRecyclerView.setItemAnimator(new DefaultItemAnimator());
        notificationRequestRecyclerView.setHasFixedSize(true);

        progressBarRequest.setVisibility(View.VISIBLE);
        new Delay(1000,new DelayDelegate(){
            public void didDelay(){
                progressBarRequest.setVisibility(View.INVISIBLE);
                notificationRequestRecyclerView.setAdapter(requestAdapter);
            }
        }).delay();


        notificationRequestRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getActivity(),notificationRequestRecyclerView,new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(),PatientRequestActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("PatientRequest", notificationRequestList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        }
        ));

    }

    /**
     * 初始化控件
     * @param view
     */

    private void initUI(View view){
        notificationViewPager = (ViewPager) view.findViewById(R.id.notification_fragment_doctor_view_pager);
        requestButton = (ImageButton) view.findViewById(R.id.notification_fragment_image_request);
        requestButton.setImageResource(R.drawable.request_selected);
        questionButton = (ImageButton) view.findViewById(R.id.notification_fragment_image_question);
        LayoutInflater layoutInflater = getActivity().getLayoutInflater().from(getActivity());
        View questionItem = layoutInflater.inflate(R.layout.item_notification_question,null);
        View requestItem = layoutInflater.inflate(R.layout.item_notification_request,null);
        notificationViewPagerList.add(questionItem);
        notificationViewPagerList.add(requestItem);
        NotificationViewPagerAdapter notificationViewPagerAdapter = new NotificationViewPagerAdapter();
        notificationViewPager.setAdapter(notificationViewPagerAdapter);
        notificationViewPager.setOnPageChangeListener(new OnNotificationPagerListener());
        notificationQuestionRecyclerView = (RecyclerView) questionItem.findViewById(R.id.doctor_notification_question_recycler_view);
        notificationRequestRecyclerView = (RecyclerView) requestItem.findViewById(R.id.doctor_notification_request_recycler_view);
        progressBarQuestion = (ProgressBar) questionItem.findViewById(R.id.notification_question_fragment_progress_bar);
        progressBarRequest = (ProgressBar) requestItem.findViewById(R.id.notification_request_fragment_progress_bar);

    }

    /**
     * viewpager 的adapter
     */
    public class NotificationViewPagerAdapter extends PagerAdapter{


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(notificationViewPagerList.get(position));
            return notificationViewPagerList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(notificationViewPagerList.get(position));
        }

        @Override
        public int getCount() {
            return notificationViewPagerList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    /**
     * 滑动监听
     */
    public class OnNotificationPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    requestButton.setImageResource(R.drawable.request_selected);
                    questionButton.setImageResource(R.drawable.question);
                    break;
                case 1:
                    questionButton.setImageResource(R.drawable.question_selected);
                    requestButton.setImageResource(R.drawable.request);
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * question recyclerview 的适配器
     */
    public class NotificationQuestionAdapter extends RecyclerView.Adapter<NotificationQuestionAdapter.ViewHolder>{

        List<Question> notificationQuestionList;

        public NotificationQuestionAdapter(List<Question> notificationQuestionList){
            this.notificationQuestionList = notificationQuestionList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_notification_question, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                Question p = notificationQuestionList.get(position);
                holder.time.setText(p.getTime());
                holder.title.setText(p.getTitle());
                holder.patient.setText(p.getPatient().getId());
        }

        @Override
        public int getItemCount() {
            return notificationQuestionList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView time,title,patient;
            public ViewHolder(View itemView) {
                super(itemView);
                time = (TextView) itemView.findViewById(R.id.notification_fragment_question_time);
                title = (TextView) itemView.findViewById(R.id.notification_fragment_question_title);
                patient = (TextView) itemView.findViewById(R.id.notification_fragment_question_patient);
            }
        }
    }
    /**
     * request recyclerview 的适配器
     */
    public class NotificationRequestAdapter extends RecyclerView.Adapter<NotificationRequestAdapter.ViewHolder>{

        List<Request> notificationRequestList;

        public NotificationRequestAdapter(List<Request> notificationRequestList){
            this.notificationRequestList = notificationRequestList;
        }



        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_notification_request, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            Request p = notificationRequestList.get(position);
            holder.time.setText(p.getTime());
            holder.title.setText(p.getContent());
            holder.patient.setText(p.getPatient().getId());

        }

        @Override
        public int getItemCount() {
            return notificationRequestList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView time,title,patient;
            public ViewHolder(View itemView) {
                super(itemView);
                time = (TextView) itemView.findViewById(R.id.notification_fragment_request_time);
                title = (TextView) itemView.findViewById(R.id.notification_fragment_request_title);
                patient = (TextView) itemView.findViewById(R.id.notification_fragment_request_patient);
            }
        }
    }


}
