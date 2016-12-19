package com.eventmeapp.eventmeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Erik Fok on 10/25/2016.
 */

public class LoginRegister extends AppCompatActivity {

    private EditText firstName, lastName, email, password, confirmPassword, loginButton;
    private Button registerButton;

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.acivity_registration);

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        confirmPassword = (EditText) findViewById(R.id.confirmPassword);
        registerButton = (Button) findViewById(R.id.regBut);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String regFirstName = firstName.getText().toString();
                final String regLastName = lastName.getText().toString();
                final String regEmail = email.getText().toString();
                final String  regPassword= firstName.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");


                            if (success) {
                                Intent sendToLoginPage = new Intent(LoginRegister.this,
                                                                    LoginScreen.class);
                                LoginRegister.this.startActivity(sendToLoginPage);
                            } else {
                                AlertDialog.Builder regInvalid = new AlertDialog.Builder(LoginRegister.this);
                                regInvalid.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(regFirstName, regLastName,
                                                        regEmail, regPassword, responseListener);
                RequestQueue requestQueue = Volley.newRequestQueue(LoginRegister.this);
                requestQueue.add(registerRequest);
            }
        });


    }



}
