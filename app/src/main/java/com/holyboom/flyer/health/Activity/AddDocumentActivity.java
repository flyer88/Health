package com.holyboom.flyer.health.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.holyboom.flyer.health.R;
import com.holyboom.flyer.health.model.PatientDocument;

/**
 * Created by flyer on 15/3/24.
 */
public class AddDocumentActivity extends ActionBarActivity{


    TextView illnessTime,illnessContent;
    Button updateDocument;
    PatientDocument patientDocument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_document);
        illnessTime = (TextView) findViewById(R.id.activity_document_time);
        illnessContent = (TextView) findViewById(R.id.activity_document_content);
        updateDocument = (Button) findViewById(R.id.activity_update_document);

        updateDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patientDocument = new PatientDocument(illnessTime.getText().toString(),illnessContent.getText().toString());
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("update_patient_document_data_return",patientDocument);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED,intent);
        finish();
    }
}
