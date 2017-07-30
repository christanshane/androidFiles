package com.example.cosmo.prelimproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences sharedpreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        Button back = (Button)findViewById(R.id.login_backbtn);
        Button login = (Button)findViewById(R.id.login_loginbtn);
        final EditText userfield = (EditText)findViewById(R.id.login_userinput);
        final EditText passfield = (EditText)findViewById(R.id.login_passinput);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = sharedpreferences.getString("UserKey",null);
                String pass = sharedpreferences.getString("PassKey",null);
                String userinput = userfield.getText().toString();
                String passinput = passfield.getText().toString();

                if(pass.equals(passinput)&&user.equals(userinput)){
                    startActivity(new Intent(Login.this, Mainmenu.class));
                    finish();
                }
                else if(!user.equals(userinput)||!pass.equals(passinput)){
                    Toast.makeText(Login.this,"Username/Password is Incorrect!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(Login.this,"The fields should not be empty!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
