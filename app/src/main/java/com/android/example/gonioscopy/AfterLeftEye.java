package com.android.example.gonioscopy;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AfterLeftEye extends AppCompatActivity {

    FloatingActionButton rightEye;
    FloatingActionButton nextPatient;
    FloatingActionButton imageUpload;
    String patient_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_left_eye);
        Bundle extras = getIntent().getExtras();
        patient_id = extras.getString("pId4");
        rightEye = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        rightEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLeftEye.this, RightEyeCamera.class);
                intent.putExtra("pId3", patient_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        nextPatient = (FloatingActionButton) findViewById(R.id.floatingActionButton4);
        nextPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLeftEye.this, LoginActivity.class);
                intent.putExtra("pId4", patient_id);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        imageUpload = (FloatingActionButton) findViewById(R.id.ImageUpload2);
        imageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AfterLeftEye.this, ImageUpload.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
