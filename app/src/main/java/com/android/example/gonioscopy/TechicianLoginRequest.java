package com.android.example.gonioscopy;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by agarw on 5/12/2017.
 */

public class TechicianLoginRequest extends StringRequest {
    private static final String LOGIN_RESQUEST_URL = "http://52.38.205.169:3000/login/technician";
    private Map<String, String> params;

    public TechicianLoginRequest(String email, String password, Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_RESQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("t_email", email);
        params.put("t_password", password);

    }



    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
