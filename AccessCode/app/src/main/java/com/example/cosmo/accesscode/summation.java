package com.example.cosmo.accesscode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class summation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summation);

        Button exit = (Button)findViewById(R.id.summation_exit);
        Button add = (Button)findViewById(R.id.summation_add);
        Button sub = (Button)findViewById(R.id.summation_subtract);
        Button mul = (Button)findViewById(R.id.summation_multiply);
        Button div = (Button)findViewById(R.id.summation_divide);
        final TextView ans = (TextView)findViewById(R.id.answerfield);
        final EditText op1 = (EditText)findViewById(R.id.operand1);
        final EditText op2 = (EditText)findViewById(R.id.operand2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double first = Integer.parseInt(op1.getText().toString());
                double second = Integer.parseInt(op2.getText().toString());

                double third = first+second;

                ans.setText(third+"");
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double first = Integer.parseInt(op1.getText().toString());
                double second = Integer.parseInt(op2.getText().toString());

                double third = first-second;

                ans.setText(third+"");
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double first = Integer.parseInt(op1.getText().toString());
                double second = Integer.parseInt(op2.getText().toString());

                double third = first*second;

                ans.setText(third+"");
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double first = Integer.parseInt(op1.getText().toString());
                double second = Integer.parseInt(op2.getText().toString());

                double third = first/second;

                ans.setText(third+"");
            }
        });
    }
}
