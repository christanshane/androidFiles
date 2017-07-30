package com.example.cosmo.prelimproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedpreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String user = sharedpreferences.getString("UserKey",null);
        String pass = sharedpreferences.getString("PassKey",null);
        Button account = (Button)findViewById(R.id.main_accountbtn);
        Button login = (Button)findViewById(R.id.main_loginbtn);
        Button exit = (Button)findViewById(R.id.main_exitbtn);

        if(user==null||pass==null){
            login.setEnabled(false);
            account.setEnabled(true);
        }else{
            login.setEnabled(true);
            account.setEnabled(true);
        }

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Account.class));
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
                finish();
            }
        });
    }
}
