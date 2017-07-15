package com.android.example.gonioscopy;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final EditText patient_contact = (EditText) findViewById(R.id.Patient_number);

        final ImageButton number_Register = (ImageButton) findViewById(R.id.b_number);

        number_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mob = patient_contact.getText().toString();

                if (!isValidPassword(mob)) {
                    patient_contact.setError("Enter 10 Digit Mobile Number");
                } else {



                    // start from here OTP
                    Response.Listener<String> responseListner = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Intent Patient = new Intent(LoginActivity.this, OtpVerification.class);
                            Patient.putExtra("pId", patient_contact.getText().toString());
                            Patient.putExtra("otp", response);
                            LoginActivity.this.startActivity(Patient);
                        }
                    };
                    otpRequest OTPRequest = new otpRequest(mob,responseListner);
                    RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                    queue.add(OTPRequest);
                    // end here OTP
                }

            }
        });
    }

    // validating for mobile number
    private boolean isValidPassword(String mob) {
        if (mob != null && mob.length() == 10) {
            return true;
        }
        return false;
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                LoginActivity.this);

        // set title
        alertDialogBuilder.setTitle("Logout");

        // set dialog message
        alertDialogBuilder
                .setMessage("Do you really want to logout?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        LoginActivity.this.finish();
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
