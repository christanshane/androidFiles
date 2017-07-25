package com.example.cosmo.prelimproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Stack;

public class Summation extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summation);

        Button one = (Button) findViewById(R.id.btn_one);
        Button two = (Button) findViewById(R.id.btn_two);
        Button three = (Button) findViewById(R.id.btn_three);
        Button four = (Button) findViewById(R.id.btn_four);
        Button five = (Button) findViewById(R.id.btn_five);
        Button six = (Button) findViewById(R.id.btn_six);
        Button seven = (Button) findViewById(R.id.btn_seven);
        Button eight = (Button) findViewById(R.id.btn_eight);
        Button nine = (Button) findViewById(R.id.btn_nine);
        Button zero = (Button) findViewById(R.id.btn_zero);

        Button clear = (Button) findViewById(R.id.btn_clear);

        Button back = (Button)findViewById(R.id.summation_backbtn);


        final Button plus = (Button) findViewById(R.id.btn_plus);
        final Button minus = (Button) findViewById(R.id.btn_minus);
        final Button multiply = (Button) findViewById(R.id.btn_multiply);
        final Button divide = (Button) findViewById(R.id.btn_divide);
        final Button equal = (Button) findViewById(R.id.btn_equals);

        final TextView  content = (TextView) findViewById(R.id.textView_content);
        final TextView num = (TextView) findViewById(R.id.txt_num1);



        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "0");
            }
        });
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "1");
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "2");
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "3");
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "4");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "5");
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "6");
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "7");
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "8");
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText(content.getText().toString() + "9");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Mainmenu.class));
                finish();
            }
        });



        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = content.getText().toString();
                if (!str.equals("")){
                    content.setText(str + "/");
                }
                if(str.endsWith("*") || str.endsWith("-") || str.endsWith("+") || str.endsWith("/")) {
                    str  = str.substring(0, str.length() - 1);
                    content.setText(str+"/");
                }
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = content.getText().toString();
                if (!str.equals("")){
                    content.setText(str + "*");
                }
                if (str.endsWith("*") || str.endsWith("-") || str.endsWith("+") || str.endsWith("/")) {
                    str = str.substring(0, str.length() - 1);
                    content.setText(str + "*");
                }
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = content.getText().toString();
                if (!str.equals("")){
                    content.setText(str + "-");
                }
                if (str.endsWith("*") || str.endsWith("-") || str.endsWith("+") || str.endsWith("/")) {
                    str  = str.substring(0, str.length() - 1);
                    content.setText(str + "-");
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = content.getText().toString();
                if (!str.equals("")){
                    content.setText(str + "+");
                }
                if (str.endsWith("*") || str.endsWith("-") || str.endsWith("+") || str.endsWith("/")) {
                    str  = str.substring(0, str.length() - 1);
                    content.setText(str + "+");
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content.setText("");
                num.setText("");



            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String input = content.getText().toString();

                input = calculatePostFix(convertInfix(Space(input)))+"";

                num.setText(input);

            }
        });

    }


    public String Space (String str){
        String temp = "";
        if (checkOperator(str.charAt(str.length()-1))) {
            str  = str.substring(0, str.length() - 1);
        }
        for (int i = 0; i < str.length(); i++){
            if (Character.isDigit(str.charAt(i))){
                temp += str.charAt(i);
            }else{
                temp += " "+str.charAt(i)+" ";
            }
        }
        return temp;
        
    }

    private boolean checkOperator(char str){
        return ("/*-+").indexOf(str)>=0;
    }

    private int precedence(char e) {

        if (e == '+' || e == '-') {
            return 1;
        } else if (e == '%' || e == '/' || e == '*') {
            return 2;
        } else {
            return -1;
        }

    }

    private int evaluateOperator(String e, int b, int a) {
        switch (e) {
            case "+":
                return (a + b);
            case "-":
                return (a - b);
            case "%":
                return (a % b);
            case "/":
                return (a / b);
            case "*":
                return (a * b);
            default:
                return 0;

        }
    }

    public String convertInfix(String infix) {

        Stack st = new Stack();

        String temp = "";

        int a = 0;
        int b = 1;


        String[] token = infix.split(" ");

        for (int i = 0; i < token.length; i++) {
            if (Character.isDigit(token[i].charAt(0))) {
                temp += token[i] + " ";
            } else if (checkOperator(token[i].charAt(0))) {
                if (!(st.isEmpty())) {
                    a= precedence(st.peek().toString().charAt(0)); // ( 2
                    b= precedence(token[i].charAt(0)); //+ 0
                }
                if (a>=b) {
                    temp+=st.pop()+" ";
                    st.push(token[i]);
                } else {
                    st.push(token[i]);
                }
            }
        }

        while (!(st.empty())) {
            temp += st.pop() + " ";
        }


        return temp;

    }

    public int calculatePostFix(String infix) {

        String[] token = infix.split(" ");


        Stack st = new Stack();

        for (int i = 0; i < token.length; i++) {

            if (Character.isDigit(token[i].charAt(0))) {

                st.push(Integer.parseInt(token[i]));

            } else if (checkOperator(token[i].charAt(0))) {

                st.push(evaluateOperator(token[i], Integer.parseInt(st.pop().toString()), Integer.parseInt(st.pop().toString())));

            }
        }
        int result = Integer.parseInt(st.pop().toString());
        return result;
    }

}
