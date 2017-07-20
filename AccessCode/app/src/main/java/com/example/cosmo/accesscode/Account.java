package com.example.cosmo.accesscode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Account extends AppCompatActivity {
    Button summation,exit;
    TextView usertext, passtext;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        usertext = (TextView)findViewById(R.id.username_holder);
        passtext = (TextView)findViewById(R.id.password_holder);
        summation = (Button)findViewById(R.id.account_summation);
        exit = (Button)findViewById(R.id.account_exit);

        sharedpreferences = getSharedPreferences(Login.MyData, Context.MODE_PRIVATE);

        usertext.setText(sharedpreferences.getString("UserKey",null));
        passtext.setText(sharedpreferences.getString("PassKey",null));

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Account.this, MainActivity.class));
            }
        });

        summation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Account.this, summation.class));
            }
        });

    }
}
