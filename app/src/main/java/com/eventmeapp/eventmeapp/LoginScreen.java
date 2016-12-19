package com.eventmeapp.eventmeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

/**
 * Created by Erik Fok on 10/24/2016.
 */

public class LoginScreen extends Activity {

    private TextView regLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.activity_login);

        EditText loginUserName = (EditText) findViewById(R.id.loginUserName);
        EditText password = (EditText) findViewById(R.id.password);
        Button loginButton = (Button) findViewById(R.id.loginBut);
        regLink = (TextView) findViewById(R.id.regLink);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(LoginScreen.this, MainActivity.class);
                LoginScreen.this.startActivity(mainIntent);
            }
        });

        regLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginScreen.this, LoginRegister.class);
                LoginScreen.this.startActivity(registerIntent);
            }

        });





    }
}
