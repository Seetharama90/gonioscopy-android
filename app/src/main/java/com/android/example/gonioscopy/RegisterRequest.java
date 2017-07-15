package com.android.example.gonioscopy;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by agarw on 5/11/2017.
 */

public class RegisterRequest extends StringRequest{

    private static final String REGISTER_RESQUEST_URL = "http://52.38.205.169:3000/login/register";
    private Map<String, String> params;

    public RegisterRequest(String patient_id, String name, String gender, String age, String email, String contact, Response.Listener<String> listener){
                super(Method.POST, REGISTER_RESQUEST_URL, listener, null);
                params = new HashMap<>();
                params.put("patient_id", patient_id);
                params.put("name", name);
                params.put("gender", gender);
                params.put("age", age + "");
                params.put("email", email);
                params.put("contact", contact + "");
    }



    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
