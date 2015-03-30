package com.holyboom.flyer.health.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.Question;

/**
 * Created by flyer on 15/3/20.
 */
public class PatientQestionActivity extends ActionBarActivity{

    TextView questionName,questionContent;
    EditText editAnswer;
    Button submitAnswer;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Question question = (Question)getIntent().getSerializableExtra("PatientQuestion");
        setContentView(R.layout.activity_patient_question);
        questionName = (TextView) findViewById(R.id.patient_question_name);
        //questionContent = (TextView) findViewById(R.id.patient_question_content);
        editAnswer = (EditText) findViewById(R.id.patient_question_answer);
        submitAnswer = (Button) findViewById(R.id.patient_question_answer_submit);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("病人提问");
        questionName.setText(question.getTitle());
       // questionContent.setText(question.getContent());

        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editAnswer.getText();
                finish();
            }
        });
    }
}
