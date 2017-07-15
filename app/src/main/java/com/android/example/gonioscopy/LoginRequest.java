package com.android.example.gonioscopy;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by agarw on 5/12/2017.
 */

public class LoginRequest extends StringRequest{
    private static final String LOGIN_RESQUEST_URL = "http://52.38.205.169:3000/login/valid";
    private Map<String, String> params;

    public LoginRequest(String patient_id, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_RESQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("patient_id", patient_id);

    }



    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
