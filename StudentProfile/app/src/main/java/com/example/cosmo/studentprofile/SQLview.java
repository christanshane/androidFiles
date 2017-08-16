package com.example.cosmo.studentprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class SQLview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlview);
        TextView idholder = (TextView) findViewById(R.id.idholder);
        TextView nameholder = (TextView) findViewById(R.id.nameholder);
        TextView programholder = (TextView) findViewById(R.id.programholder);
        TextView yearholder = (TextView) findViewById(R.id.yearholder);
        StudentInfo info = new StudentInfo(this);
        info.open();
        String[] data = info.allData();
        idholder.setText(data[0]);
        nameholder.setText(data[1]);
        programholder.setText(data[2]);
        yearholder.setText(data[3]);
        info.close();
    }
}
