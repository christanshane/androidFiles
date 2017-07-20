package com.example.cosmo.prelimproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        final SharedPreferences sharedpreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        Button append = (Button)findViewById(R.id.account_appendbtn);
        Button back = (Button)findViewById(R.id.account_backbtn);
        final EditText userfield = (EditText)findViewById(R.id.account_userfield);
        final EditText passfield = (EditText)findViewById(R.id.account_passfield);

        append.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                String username = userfield.getText().toString();
                String password = passfield.getText().toString();

                editor.putString("UserKey",username);
                editor.putString("PassKey",password);
                editor.commit();

                startActivity(new Intent(Account.this, MainActivity.class));
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Account.this, MainActivity.class));
                finish();
            }
        });
    }
}
