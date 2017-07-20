package com.example.cosmo.accesscode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String user,pass;
    boolean exists = false;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnlogin = (Button)findViewById(R.id.btn_login);
        Button btnaccount = (Button)findViewById(R.id.btn_account);
        Button btnexit = (Button)findViewById(R.id.main_quit);
        TextView logout = (TextView)findViewById(R.id.logout_btn);



        final SharedPreferences sharedpreferences = getSharedPreferences(Login.MyData, Context.MODE_PRIVATE);

        user=sharedpreferences.getString("UserKey", null);
        pass=sharedpreferences.getString("PassKey", null);
        if(user==null && pass == null) {
            btnlogin.setEnabled(true);
            btnaccount.setEnabled(false);
        }else{
            btnlogin.setEnabled(false);
            btnaccount.setEnabled(true);
            exists = true;
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

        btnaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Account.class));
            }
        });

        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.clear();
                editor.commit();
                System.exit(0);
            }
        });


    }
}
