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

public class Mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        final SharedPreferences sharedpreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        Button calculator = (Button)findViewById(R.id.main_calc);
        Button logout = (Button)findViewById(R.id.main_logout);
        Button delete = (Button)findViewById(R.id.main_delete);
        TextView user = (TextView)findViewById(R.id.mainmenu_user);
        user.setText("Welcome, " + sharedpreferences.getString("UserKey",null) + "!");

        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Summation.class));
                finish();
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Mainmenu.this,MainActivity.class));
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(Mainmenu.this,"You have logged out", Toast.LENGTH_LONG);
                            startActivity(new Intent(Mainmenu.this, MainActivity.class));
                            finish();
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            Toast.makeText(Mainmenu.this,"Canceled ", Toast.LENGTH_SHORT).show();
                            break;
                    }
            }
        };
                AlertDialog.Builder builder = new AlertDialog.Builder(Mainmenu.this);
                builder.setMessage("Are you sure you want to delete your account?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }
}
