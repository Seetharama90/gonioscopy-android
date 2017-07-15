package com.android.example.gonioscopy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

public class OtpVerification extends AppCompatActivity {
    EditText otpnumber;
    ImageButton b_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);

        Bundle extras = getIntent().getExtras();
        final String patient_id = extras.getString("pId");
        final String otp = extras.getString("otp");

        otpnumber = (EditText) findViewById(R.id.otp);
        b_otp =(ImageButton) findViewById(R.id.b_otp);
        b_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = otpnumber.getText().toString();
                if (number.equals(otp)){
                    Response.Listener<String> responseListner = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            if (response.equals("Valid")){
                                Intent validPatient = new Intent(OtpVerification.this, UserAreaActivity.class);
                                validPatient.putExtra("pId1", patient_id);
                                validPatient.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(validPatient);
                                finish();

                            }else {
                                Intent invalidPatient = new Intent(OtpVerification.this, RegisterActivity.class);
                                invalidPatient.putExtra("pId", patient_id);
                                invalidPatient.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(invalidPatient);
                                finish();
                            }
                        }
                    };
                    LoginRequest loginRequest = new LoginRequest(patient_id,responseListner);
                    RequestQueue queue = Volley.newRequestQueue(OtpVerification.this);
                    queue.add(loginRequest);
                } else{
                    Toast toast = Toast.makeText(getApplicationContext(),"OTP expired, Try again!",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}
