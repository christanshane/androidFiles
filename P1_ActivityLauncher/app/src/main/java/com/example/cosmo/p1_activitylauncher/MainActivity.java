package com.example.cosmo.p1_activitylauncher;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.btnLaunch);
        b.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                startActivity(new Intent(MainActivity.this, second.class));
            }
        });
    }
//        public boolean onCreateOptionsMenu(Menu menu){
//            getMenuInflater().inflate(R.menu.menu_main,menu);
//            return true;
//        }
    }
