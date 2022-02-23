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

    Button pow;
    String function;
    Dialog dg;
    EditText func;
    EditText a;
    EditText b;
    int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_function);

        //Button key;

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

        pow = findViewById(R.id.Pow);
        pow.setText(Html.fromHtml("a<sup><small>b</small></sup>"));

        dg = new Dialog(this);
        dg.setContentView(R.layout.dialog);
        dg.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        function = "";

        Button submitPow = dg.findViewById(R.id.submitPow);
        Button cancel = dg.findViewById(R.id.cancel);

        a = dg.findViewById(R.id.a);
        b = dg.findViewById(R.id.b);

        TextView txt = dg.findViewById(R.id.Title);
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
                dg.dismiss();
            }
        });



    }

    public void powOnClick(){
        function = func.getText().toString();
        dg.show();
    }

    public void putPow(){
        function += a.getText().toString();
        function += '^';
        function += b.getText().toString();
        func.setText(function);
        dg.dismiss();
    }

    public void putX(View v){
        function += 'x';
        func.setText(function);
    }

    public void putDecimalPoint(View v){
        function += '.';
        func.setText(function);
    }

    public void putNum(int num){
        function += num;
        func.setText(function);
    }



}