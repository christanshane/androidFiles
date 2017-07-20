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
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences sharedpreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        Button logout = (Button)findViewById(R.id.login_logoutbtn);
        Button back = (Button)findViewById(R.id.login_backbtn);

        TextView username = (TextView)findViewById(R.id.login_username);
        username.setText(sharedpreferences.getString("UserKey",null));

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i){
                            case DialogInterface.BUTTON_POSITIVE:
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("UserKey",null);
                                editor.putString("PassKey",null);
                                editor.commit();
                                Toast.makeText(Login.this,"You have logged out", Toast.LENGTH_LONG);
                                startActivity(new Intent(Login.this, MainActivity.class));
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(Login.this,"Canceled ", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setMessage("Are you sure you want to logout?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
            }
        });
    }
}
