package com.android.example.gonioscopy;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bundle extras = getIntent().getExtras();
        final String patient_id = extras.getString("pId");
        final TextView patient_number = (TextView) findViewById(R.id.mobileNumber);
        patient_number.setText(patient_id);
        final EditText patient_name = (EditText) findViewById(R.id.name);
        final EditText patient_gender = (EditText) findViewById(R.id.gender);
        final EditText patient_age = (EditText) findViewById(R.id.age);
        final EditText patient_email = (EditText) findViewById(R.id.email);
        final EditText patient_contact = (EditText) findViewById(R.id.contact);
        patient_contact.setText(patient_id);
        final Button bRegister = (Button) findViewById(R.id.register);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String id = patient_id;
                final String name = patient_name.getText().toString();
                final String gender = patient_gender.getText().toString();
                final String age = patient_age.getText().toString();
                final String email = patient_email.getText().toString();
                final String contact = patient_contact.getText().toString();

                if (!isValidName(name)) {
                    patient_name.setError("Enter Name");
                }
                else if (!isValidGender(gender)) {
                    patient_gender.setError("Enter Gender");
                }
                else if (!isValidAge(age)) {
                    patient_age.setError("Enter age");
                }
                else if (!isValidEmail(email)) {
                    patient_email.setError("Invalid Email");
                }
                else if (!isValidPassword(contact)) {
                    patient_contact.setError("Invalid Email");
                } else {
                    Response.Listener<String> responseListner = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                if (jsonResponse != null) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Registration Successfully", Toast.LENGTH_SHORT);
                                    toast.show();
                                    Intent intent = new Intent(RegisterActivity.this, UserAreaActivity.class);
                                    intent.putExtra("pId1", patient_id);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
//                            boolean success = jsonResponse.getBoolean("success");

//                            if (success){
//                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                RegisterActivity.this.startActivity(intent);
//                            }else {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
//                                builder.setMessage("Register Failed")
//                                        .setNegativeButton("Retry", null)
//                                        .create()
//                                        .show();
//                            }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    RegisterRequest registerRequest = new RegisterRequest(id, name, gender, age, email, contact, responseListner);
                    RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                    queue.add(registerRequest);
                }

            }
        });
    }

    private boolean isValidName(String name) {
        if (name != null && name.length() > 1) {
            return true;
        }
        return false;
    }

    private boolean isValidGender(String gender) {
        if (gender != null && gender.length() > 1) {
            return true;
        }
        return false;
    }

    private boolean isValidAge(String age) {
        if (age != null && age.length() > 0) {
            return true;
        }
        return false;
    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // validating for mobile number
    private boolean isValidPassword(String contact) {
        if (contact != null && contact.length() == 10) {
            return true;
        }
        return false;
    }
}
