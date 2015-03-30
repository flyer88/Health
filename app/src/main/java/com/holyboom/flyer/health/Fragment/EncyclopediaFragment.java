package com.holyboom.flyer.health.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.holyboom.flyer.health.Activity.EditEncylopediaActivity;
import com.holyboom.flyer.health.Activity.PublishedEncylopediaActivity;
import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.EncylopediaInformation;
import com.holyboom.flyer.health.model.Metting;
import com.holyboom.flyer.health.uitil.Delay;
import com.holyboom.flyer.health.uitil.DelayDelegate;
import com.holyboom.flyer.health.uitil.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flyer on 15/3/17.
 */
public class EncyclopediaFragment extends Fragment{

    ViewPager encylopediaViewPager;
    List<View> encylopediaViewPagerList = new ArrayList<View>() ;

    RecyclerView encylopediaRecylerView;
    List<EncylopediaInformation> encylopediaInformationList = new ArrayList<EncylopediaInformation>();

    com.getbase.floatingactionbutton.FloatingActionButton addEncylopedia;
    EncylopediaAdapter encylopediaAdapter;
    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_encyclopedia,container,false);

        initUI(view);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //加载progressbar
        encylopediaInformationList.add(new EncylopediaInformation("高血压", "正常人的血压随内外环境变化在一定范围内波动。在整体人群，血压水平随年龄逐渐升高，以收缩压更为明显，但50岁后舒张压呈现下降趋势，脉压也随之加大。近年来，人们对心血管病多重危险因素作用以及心、脑、肾靶器官保护的认识不断深入，高血压的诊断标准也在不断调整，目前认为同一血压水平的患者发生心血管病的危险不同，因此有了血压分层的概念，即发生心血管病危险度不同的患者，适宜血压水平应有不同。医生面对患者时在参考标准的基础上，根据其具体情况判断该患者最合适的血压范围，采用针对性的治疗措施。"));
        encylopediaInformationList.add(new EncylopediaInformation("高血脂", "高脂血症是指血脂水平过高，可直接引起一些严重危害人体健康的疾病，如动脉粥样硬化、冠心病、胰腺炎等。"));
        encylopediaInformationList.add(new EncylopediaInformation("高血糖","当血糖值高于正常范围即为高血糖。高血糖也是通常大家所说“三高”中的一高。另外“两高”分别是高血压和高脂血症。空腹血糖正常值在6.1mmol/L以下，餐后两小时血糖的正常值在7.8mmol/L以下，如果高于这一范围，称为高血糖。"));
        encylopediaInformationList.add(new EncylopediaInformation("心脏病","心脏病是一类比较常见的循环系统疾病。循环系统由心脏、血管和调节血液循环的神经体液组织构成，循环系统疾病也称为心血管病，包括上述所有组织器官的疾病，在内科疾病中属于常见病，其中以心脏病最为多见，能显著地影响患者的劳动力。"));
        encylopediaInformationList.add(new EncylopediaInformation("盲肠炎","正确的名称应该是急性阑尾炎，通俗称盲肠炎。阑尾约长三至四吋（七十五至一百毫米），是自大肠初段长出 的管状器官。阑尾炎分为急性和慢性两种。急性阑尾炎起病突然，必须立刻动手术。"));
        encylopediaInformationList.add(new EncylopediaInformation("糖尿病","糖尿病是一组以高血糖为特征的代谢性疾病。高血糖则是由于胰岛素分泌缺陷或其生物作用受损，或两者兼有引起。糖尿病时长期存在的高血糖，导致各种组织，特别是眼、肾、心脏、血管、神经的慢性损害、功能障碍。"));

        encylopediaAdapter = new EncylopediaAdapter(encylopediaInformationList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        encylopediaRecylerView.setLayoutManager(linearLayoutManager);
        encylopediaRecylerView.setItemAnimator(new DefaultItemAnimator());
        encylopediaRecylerView.setHasFixedSize(true);

        progressBar.setVisibility(View.VISIBLE);
        new Delay(1000,new DelayDelegate(){
            public void didDelay(){
                progressBar.setVisibility(View.INVISIBLE);
                encylopediaRecylerView.setAdapter(encylopediaAdapter);
            }
        }).delay();


        addEncylopedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditEncylopediaActivity.class);
                startActivityForResult(intent, 2);
            }
        });

        encylopediaRecylerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), encylopediaRecylerView, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getActivity(), PublishedEncylopediaActivity.class);
                bundle.putSerializable("EncylopediaInformation",encylopediaInformationList.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 2:
                if (resultCode == getActivity().RESULT_OK){

                    EncylopediaInformation encylopediaInformation = (EncylopediaInformation)
                            data.getSerializableExtra("add_encylopedia_data_return");
                    //Log.e("MettingFragment 返还数据", metting.getTime());
                    encylopediaInformationList.add(encylopediaInformation);
                    encylopediaAdapter = new EncylopediaAdapter(encylopediaInformationList);
                    encylopediaRecylerView.setAdapter(encylopediaAdapter);
                }
                break;
            default:
        }
    }

    private void initUI(View view){

        encylopediaRecylerView  = (RecyclerView) view.findViewById(R.id.encylopedia_fragment_recyler_view);
        addEncylopedia = (com.getbase.floatingactionbutton.FloatingActionButton) view.findViewById(R.id.add_encyclopedia);
        progressBar = (ProgressBar) view.findViewById(R.id.encyclopedia_fragment_progress_bar);
    }

    /**
     * recyclerView 的适配器
     */
    public class EncylopediaAdapter extends RecyclerView.Adapter<EncylopediaAdapter.ViewHolder>{

        List<EncylopediaInformation> encylopediaInformationList;

        public EncylopediaAdapter(List<EncylopediaInformation> encylopediaInformationList){
            this.encylopediaInformationList = encylopediaInformationList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_encylopedia_published_, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            EncylopediaInformation p = encylopediaInformationList.get(position);
            holder.title.setText(p.getTitle());
            holder.content.setText(p.getContent());
        }

        @Override
        public int getItemCount() {
            return encylopediaInformationList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            TextView title,content;
            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.encylopedia_published_fragment_title);
                content = (TextView) itemView.findViewById(R.id.encylopedia_published_fragment_content);
            }
        }



    }

}
