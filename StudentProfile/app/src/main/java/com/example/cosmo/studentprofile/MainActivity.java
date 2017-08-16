package com.example.cosmo.studentprofile;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button searchbtn,findbtn,newbtn,savebtn,deletebtn,viewbtn,exitbtn;
    EditText idTxt,nameTxt,programTxt,yearTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchbtn = (Button)findViewById(R.id.searchbtn);
        findbtn = (Button)findViewById(R.id.findbtn);
        newbtn = (Button)findViewById(R.id.newbtn);
        savebtn = (Button)findViewById(R.id.savebtn);
        deletebtn = (Button)findViewById(R.id.deletebtn);
        viewbtn = (Button)findViewById(R.id.viewbtn);
        exitbtn = (Button)findViewById(R.id.exitbtn);

        idTxt = (EditText)findViewById(R.id.idTxt);
        nameTxt = (EditText)findViewById(R.id.nameTxt);
        programTxt = (EditText)findViewById(R.id.programTxt);
        yearTxt = (EditText)findViewById(R.id.yearTxt);

        searchbtn.setOnClickListener(this);
        findbtn.setOnClickListener(this);
        newbtn.setOnClickListener(this);
        savebtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
        viewbtn.setOnClickListener(this);
        exitbtn.setOnClickListener(this);

        findbtn.setEnabled(false);
        savebtn.setEnabled(false);
        deletebtn.setEnabled(false);

        disableBoxes();
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.searchbtn:
                clearBoxes();
                idTxt.requestFocus();
                searchbtn.setEnabled(false);
                findbtn.setEnabled(true);
                idTxt.setEnabled(true);
                newbtn.setEnabled(false);
                break;
            case R.id.findbtn:
                String id = "";
                String returnedLevel = "";
                String returnedName = "";
                String returnedProgram = "";
                id = idTxt.getText().toString();
                StudentInfo viewEntry = new StudentInfo(MainActivity.this);
                viewEntry.open();
                if(viewEntry.exists(id)){
                    returnedName = viewEntry.getName(id);
                    returnedProgram = viewEntry.getProgram(id);
                    returnedLevel = viewEntry.getLevel(id);
                    viewEntry.close();

                    idTxt.setText(id);
                    nameTxt.setText(returnedName);
                    programTxt.setText(returnedProgram);
                    yearTxt.setText(returnedLevel);

                    enableBoxes();

                    newbtn.setEnabled(false);
                    savebtn.setEnabled(true);
                    deletebtn.setEnabled(true);
                    findbtn.setEnabled(false);
                }else {
                    Toast.makeText(getApplicationContext(), "ID does not exist!", Toast.LENGTH_LONG).show();
                    clearBoxes();
                    idTxt.setEnabled(false);
                    searchbtn.setEnabled(true);
                    newbtn.setEnabled(true);
                    findbtn.setEnabled(false);
                }
                break;
            case R.id.newbtn:
                newbtnPressed(this);
                break;
            case R.id.savebtn:
                if(isEntryValid()){
                    savebtnPressed(this);
                }else{
                    Toast.makeText(MainActivity.this,"Entry invalid!",Toast.LENGTH_SHORT);
                    if(idTxt.getText().toString().length()==0){
                        idTxt.requestFocus();
                    }else if(nameTxt.getText().toString().length()==0){
                        nameTxt.requestFocus();
                    }else if(programTxt.getText().toString().length()==0){
                        programTxt.requestFocus();
                    }else{
                        yearTxt.requestFocus();
                    }
                }
                break;
            case R.id.deletebtn:
                if(isEntryValid()){
                    deletebtnPressed(this);
                    disableBoxes();
                }else{
                    Toast.makeText(MainActivity.this,"Entry invalid!",Toast.LENGTH_SHORT);
                    if(idTxt.getText().toString().length()==0){
                        idTxt.requestFocus();
                    }else if(nameTxt.getText().toString().length()==0){
                        nameTxt.requestFocus();
                    }else if(programTxt.getText().toString().length()==0){
                        programTxt.requestFocus();
                    }else{
                        yearTxt.requestFocus();
                    }
                }
                break;
            case R.id.viewbtn:
                startActivity(new Intent(MainActivity.this, SQLview.class));
                break;
            case R.id.exitbtn:
                finish();
                break;
        }
    }

    public void savebtnPressed(final Context c){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to save/update profile?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                boolean didItWork = true;
                String update = "Success!";
                try {
                    String inputid = idTxt.getText().toString();
                    String name = nameTxt.getText().toString();
                    String program = programTxt.getText().toString();
                    String level = yearTxt.getText().toString();
                    StudentInfo entry = new StudentInfo(MainActivity.this);
                    entry.open();
                    if(entry.exists(inputid)){
                        entry.updateEntry(inputid,name,program,level);
                        entry.close();
                        update = "Updated!";
                    }else{
                        entry.createEntry(inputid, name, program, level);
                        update = "Success!";
                        entry.close();
                    }
                } catch (Exception e) {
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(c);
                    d.setTitle("Error Notification");
                    TextView tv = new TextView(c);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (didItWork) {
                        Dialog d = new Dialog(c);
                        d.setTitle("Record has been added in the database.");
                        TextView tv = new TextView(c);
                        tv.setText(update);
                        d.setContentView(tv);
                        d.show();
                        savebtn.setEnabled(false);
                        deletebtn.setEnabled(false);
                        clearBoxes();
                        disableBoxes();
                        searchbtn.setEnabled(true);
                        newbtn.setEnabled(true);
                        viewbtn.setEnabled(true);
                    }
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),"Cancelled",Toast.LENGTH_SHORT);
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void deletebtnPressed(Context c){AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to delete profile?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                String delrow = idTxt.getText().toString();
                newbtn.setEnabled(true);
                savebtn.setEnabled(false);
                deletebtn.setEnabled(false);
                searchbtn.setEnabled(true);
                StudentInfo dr = new StudentInfo(MainActivity.this);
                dr.open();
                if(dr.exists(delrow)){
                    dr.deleteEntry(delrow);
                    dr.close();
                }else{
                    Toast.makeText(getApplicationContext(),"Entry does not Exist!",Toast.LENGTH_SHORT);
                }
                clearBoxes();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),"Cancelled",Toast.LENGTH_SHORT);
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void newbtnPressed(Context c){AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to create a profile?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                clearBoxes();
                idTxt.requestFocus();
                newbtn.setEnabled(false);
                viewbtn.setEnabled(false);
                savebtn.setEnabled(true);
                searchbtn.setEnabled(false);
                enableBoxes();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(),"Cancelled",Toast.LENGTH_SHORT);
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public boolean isEntryValid(){
        boolean result = true;
        int id = idTxt.getText().toString().length();
        int name = nameTxt.getText().toString().length();
        int prog = programTxt.getText().toString().length();
        int level = yearTxt.getText().toString().length();
        if(id == 0 || name == 0 || prog == 0 || level == 0){
            result = false;
        }
        return result;
    }

    public void clearBoxes(){
        idTxt.setText("");
        nameTxt.setText("");
        programTxt.setText("");
        yearTxt.setText("");
    }

    public void disableBoxes(){
        idTxt.setEnabled(false);
        nameTxt.setEnabled(false);
        programTxt.setEnabled(false);
        yearTxt.setEnabled(false);
    }

    public void enableBoxes(){
        idTxt.setEnabled(true);
        nameTxt.setEnabled(true);
        programTxt.setEnabled(true);
        yearTxt.setEnabled(true);
    }

}
