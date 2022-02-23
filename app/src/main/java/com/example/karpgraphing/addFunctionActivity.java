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

public class addFunctionActivity extends AppCompatActivity {
    Button log;
    Button pow;
    String function;
    Dialog powDialog;
    EditText func;
    EditText a;
    EditText b;
    int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_function);


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

        func = findViewById(R.id.func);
        Button submit = findViewById(R.id.submit);

        log = findViewById(R.id.logBtn);
        log.setText(Html.fromHtml("\uD835\uDC59\uD835\uDC5C\uD835\uDC54<sub><small>\uD835\uDC4E</small></sub>\uD835\uDC4F"));

        //pow func button init
        pow = findViewById(R.id.Pow);
        pow.setText(Html.fromHtml("\uD835\uDC4E<sup><small>\uD835\uDC4F</small></sup>"));

        powDialog = new Dialog(this);
        powDialog.setContentView(R.layout.dialog);
        powDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        function = "";

        Button submitPow = powDialog.findViewById(R.id.submitPow);
        Button cancel = powDialog.findViewById(R.id.cancel);

        a = powDialog.findViewById(R.id.a);
        b = powDialog.findViewById(R.id.b);

        TextView txt = powDialog.findViewById(R.id.Title);
        txt.setText(Html.fromHtml("a<sup><small>b</small></sup>"));

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