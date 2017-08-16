package com.example.cosmo.androiddatabaseapplication;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class SQLiteExample extends AppCompatActivity implements OnClickListener {
    Button btnUpdate, btnView, btnGetInfo, btnEdit, btnDelete;
    EditText txtName, txtProgram, txtRowId, txtLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_example);
        txtName = (EditText)findViewById(R.id.txtName);
        txtProgram = (EditText)findViewById(R.id.txtProgram);
        txtLevel = (EditText)findViewById(R.id.txtLevel);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnView = (Button)findViewById(R.id.btnView);
        btnUpdate.setOnClickListener(this);
        btnView.setOnClickListener(this);
        txtRowId = (EditText)findViewById(R.id.txtRowId);
        btnGetInfo = (Button)findViewById(R.id.btnGetInfo);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        btnGetInfo.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    public void onClick(View arg0){
        switch (arg0.getId()) {
            case R.id.btnUpdate:
                boolean didItWork = true;
                try {
                    String name = txtName.getText().toString();
                    String program = txtProgram.getText().toString();
                    String level = txtLevel.getText().toString();

                    StudentInfo entry = new StudentInfo(SQLiteExample.this);
                    entry.open();
                    entry.createEntry(name, program, level);
                    entry.close();
                } catch (Exception e) {
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Error Notification");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (didItWork) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Record has been added in the database.");
                        TextView tv = new TextView(this);
                        tv.setText("Success!");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
            case R.id.btnView:
                startActivity(new Intent(SQLiteExample.this, SQLView.class));
                break;
            case R.id.btnGetInfo:
                String s = txtRowId.getText().toString();
                Long l = Long.parseLong(s);
                StudentInfo si = new StudentInfo(this);
                si.open();
                String returnedName = si.getName(l);
                String returnedProgram = si.getProgram(l);
                String returnedLevel = si.getLevel(l);
                si.close();

                txtName.setText(returnedName);
                txtProgram.setText(returnedProgram);
                txtLevel.setText(returnedLevel);
                break;
            case R.id.btnEdit:
                String sname = txtName.getText().toString();
                String sprogram = txtProgram.getText().toString();
                String srow = txtRowId.getText().toString();
                String slevel = txtLevel.getText().toString();
                Long lrow = Long.parseLong(srow);

                StudentInfo ed = new StudentInfo(this);
                ed.open();
                ed.updateEntry(lrow, sname, sprogram, slevel);
                ed.close();
                break;
            case R.id.btnDelete:
                String drow = txtRowId.getText().toString();
                Long delrow = Long.parseLong(drow);

                StudentInfo dr = new StudentInfo(this);
                dr.open();
                dr.deleteEntry(delrow);
                dr.close();
                break;
        }
    }
}
