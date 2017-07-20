package com.example.cosmo.accesscode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText input_username, input_password;
    Button append;
    Intent in;

    public static final String MyData = "MyData";
    public static final String Username = "UserKey";
    public static final String Password = "PassKey";
    public static final String Account = "AccountKey";
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        input_username = (EditText)findViewById(R.id.input_username);
        input_password = (EditText)findViewById(R.id.input_password);

        append = (Button)findViewById(R.id.btn_summation);
        sharedpreferences = getSharedPreferences(MyData, Context.MODE_PRIVATE);

        append.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = input_username.getText().toString();
                String pass = input_password.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Username, user);
                editor.putString(Password, pass);
                editor.commit();

                in = new Intent(Login.this, MainActivity.class);
                startActivity(in);
            }
        });

    }
}
