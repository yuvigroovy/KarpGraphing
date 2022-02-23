package com.example.karpgraphing;

/***************************************************************************
              _   __                   _____                 _     _
             | | / /                  |  __ \               | |   (_)
            | |/ /  __ _ _ __ _ __   | |  \/_ __ __ _ _ __ | |__  _ _ __   __ _
           |    \ / _` | '__| '_ \  | | __| '__/ _` | '_ \| '_ \| | '_ \ / _` |
          | |\  \ (_| | |  | |_) | | |_\ \ | | (_| | |_) | | | | | | | | (_| |
         |_| \_/\__,_|_|  | .__/   \____/_|  \__,_| .__/|_| |_|_|_| |_|\__, |
                         | |                     | |                   __/ |
                         |_|                     |_|                  |___/

 ***************************************************************************/

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Objects;

public class addFunctionActivity extends AppCompatActivity {
    Button log;
    Button pow;
    String function;
    String parsedFunctions;
    Dialog powDialog;
    EditText func;
    EditText a;
    EditText b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_function);

        //remove app title
        Objects.requireNonNull(getSupportActionBar()).hide();

        //numpad init
        TableLayout keypad = findViewById(R.id.keypad);
        for (int i = 0; i < 9; i++) {
            Button key = keypad.findViewWithTag(i+"");
            key.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    putNum(Integer.parseInt((String) key.getTag()));
                }
            });
        }

        func = findViewById(R.id.function);
        Button submit = findViewById(R.id.submit);

        log = findViewById(R.id.logBtn);
        log.setText(Html.fromHtml(MathFont.l + MathFont.o + MathFont.g +"<sub><small>"+ MathFont.a +"</small></sub>" + MathFont.b));

        //pow func button init
        pow = findViewById(R.id.Pow);
        pow.setText(Html.fromHtml(MathFont.a + "<sup><small>" + MathFont.b + "</small></sup>"));

        powDialog = new Dialog(this);
        powDialog.setContentView(R.layout.dialog);
        powDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        function = "";

        Button submitPow = powDialog.findViewById(R.id.submitPow);
        Button cancel = powDialog.findViewById(R.id.cancel);

        a = powDialog.findViewById(R.id.a);
        b = powDialog.findViewById(R.id.b);

        TextView txt = powDialog.findViewById(R.id.Title);
        txt.setText(Html.fromHtml(MathFont.a + "<sup><small>" + MathFont.b + "</small></sup>"));

        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                powOnClick();
            }
        });

        //intent
        Intent in = getIntent();
        Intent addFunc = new Intent();




        //user input

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFunc.putExtra("func",func.getText().toString());
                setResult(RESULT_OK, addFunc);
                finish();
            }
        });

        submitPow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putPow();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                powDialog.dismiss();
            }
        });



    }

    public void powOnClick(){
        function = func.getText().toString();
        powDialog.show();
    }

    public void putPow(){
        function += a.getText().toString() + "^(" + b.getText().toString() + ')';
        func.setText(function);
        powDialog.dismiss();
    }

    public void putX(View v){
        function += 'x';
        func.setText(function);
    }

    public void putDecimalPoint(View v){
        function += '.';
        func.setText(function);
    }

    public void putAdd(View v){
        function += '+';
        func.setText(function);
    }

    public void putSub(View v){
        function += '-';
        func.setText(function);
    }

    public void putMul(View v){
        function += '*';
        func.setText(function);
    }

    public void putDiv(View v){
        function += '/';
        func.setText(function);
    }

    public void putNum(int num){
        function += num;
        func.setText(function);
    }



}