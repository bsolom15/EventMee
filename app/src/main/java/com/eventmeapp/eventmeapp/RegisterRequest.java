package com.eventmeapp.eventmeapp;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Erik Fok on 10/26/2016.
 */

public class RegisterRequest extends StringRequest {

    private final static String REGISTER_REQUEST_URL = "http://eventme.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String firstName, String lastName, String email, String password,
    Response.Listener<String> listener) {

        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("First Name", firstName);
        params.put("Last Name", lastName);
        params.put("Email", email);
        params.put("Password", password);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
