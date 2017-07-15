package com.android.example.gonioscopy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by agarw on 7/1/2017.
 */


public class otpRequest extends StringRequest {

    private static final String REGISTER_RESQUEST_URL = "http://52.38.205.169:3000/otp";
    private Map<String, String> params;

    public otpRequest(String mob, Response.Listener<String> listener){
        super(Method.POST, REGISTER_RESQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("mobile", mob);
    }



    @Override
    public Map<String, String> getParams() {
        return params;
    }
}