package com.android.example.gonioscopy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    FloatingActionButton leftEye;
    FloatingActionButton rightEye;
    TextView Userid;
    String patient_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        Bundle extras = getIntent().getExtras();
        patient_id = extras.getString("pId1");
        Userid = (TextView) findViewById(R.id.UserName);
        Userid.setText(patient_id);
        leftEye = (FloatingActionButton) findViewById(R.id.leftEye);
        rightEye = (FloatingActionButton) findViewById(R.id.rightEye);
        leftEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leye = new Intent(UserAreaActivity.this, LeftEyeCamera.class);
                leye.putExtra("pId2", patient_id);
                startActivity(leye);

            }
        });
        rightEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reye = new Intent(UserAreaActivity.this, RightEyeCamera.class);
                reye.putExtra("pId3", patient_id);
                startActivity(reye);

            }
        });
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                UserAreaActivity.this);

        // set title
        alertDialogBuilder.setTitle("Exit");

        // set dialog message
        alertDialogBuilder
                .setMessage("Do you really want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        UserAreaActivity.this.finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }
}
