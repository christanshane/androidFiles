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
import android.widget.TextView;

import org.w3c.dom.Text;

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
        final TextView errordisplay = (TextView)findViewById(R.id.errorcode);
        userfield.setText(sharedpreferences.getString("UserKey",null));
        passfield.setText(sharedpreferences.getString("PassKey",null));

        append.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                String username = userfield.getText().toString();
                String password = passfield.getText().toString();
                if(username.length()==0){
                    errordisplay.setText("Username is empty!");
                    userfield.requestFocus();
                }else if(password.length()==0){
                    errordisplay.setText("Password is empty!");
                    passfield.requestFocus();
                }else if(username.equals(password)) {
                    errordisplay.setText("Your password is the same as your username, this is not allowed.");
                }
                else{
                    editor.putString("UserKey",username);
                    editor.putString("PassKey",password);
                    editor.commit();
                    startActivity(new Intent(Account.this, MainActivity.class));
                    finish();
                }
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
