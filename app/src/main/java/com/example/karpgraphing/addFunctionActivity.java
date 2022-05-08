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
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Objects;

public class addFunctionActivity extends AppCompatActivity {
    private History historyDb;
    Button openBr;
    Button closeBr;
    Button log;
    Button pow;
    Button sin;
    Button cos;
    Button tan;
    Button submitDialog;
    String function;
    String parsedFunction;
    Dialog dialog;
    EditText func;
    EditText a;
    EditText b;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_function);

        //remove app title
        Objects.requireNonNull(getSupportActionBar()).hide();

        //DataBase init
        historyDb = new History(this);

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

        //brackets buttons init
        openBr = findViewById(R.id.opbr);
        closeBr = findViewById(R.id.clbr);

        //sin button init
        sin = findViewById(R.id.sin);
        sin.setText(MathFont.s + MathFont.i + MathFont.n);

        //cos button init
        cos = findViewById(R.id.cos);
        cos.setText(MathFont.c + MathFont.o + MathFont.s);

        //tan button init
        tan = findViewById(R.id.tangense);
        tan.setText(MathFont.t + MathFont.a + MathFont.n);

        //log function button init
        log = findViewById(R.id.logBtn);
        log.setText(Html.fromHtml(MathFont.l + MathFont.o + MathFont.g +"<sub><small>"+ MathFont.a +"</small></sub>" + MathFont.b));

        //pow function button init
        pow = findViewById(R.id.Pow);
        pow.setText(Html.fromHtml(MathFont.a + "<sup><small>" + MathFont.b + "</small></sup>"));

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        function = "";
        parsedFunction = "";

        submitDialog = dialog.findViewById(R.id.submitDialog);
        Button cancel = dialog.findViewById(R.id.cancel);

        a = dialog.findViewById(R.id.a);
        b = dialog.findViewById(R.id.b);

        txt = dialog.findViewById(R.id.Title);

        //brackets buttons onclick
        openBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putOpenBr();
            }
        });
        closeBr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putCloseBr();
            }
        });

        //pow function button onclick
        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                powOnClick();
            }
        });

        //log function button onclick
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOnClick();
            }
        });

        //sin function button onclick
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putSin();
            }
        });

        //cos function button onclick
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putCos();
            }
        });

        //tan function button onclick
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putTan();
            }
        });

        //intent function to main activity
        Intent in = getIntent();
        Intent addFunc = new Intent();

        // submit function to main activity
        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                historyDb.createFunction(new HistoryItem(function));
                addFunc.putExtra("func",function);
                setResult(RESULT_OK, addFunc);
                finish();
            }
        });

        //cancel dialog
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    public void logOnClick(){
        function = func.getText().toString();
        txt.setText(Html.fromHtml(MathFont.l +MathFont.o + MathFont.g + "<sub><small>" + MathFont.a + "</small></sub>" + MathFont.b));

        submitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putLog();
            }
        });

        dialog.show();
    }

    public void powOnClick(){
        function = func.getText().toString();
        txt.setText(Html.fromHtml(MathFont.a + "<sup><small>" + MathFont.b + "</small></sup>"));

        submitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putPow();
            }
        });

        dialog.show();
    }

    public void putOpenBr(){
        function += "(";
        parsedFunction += "(";
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putCloseBr(){
        function += ")";
        parsedFunction += ")";
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putPow(){
        function += a.getText().toString() + "^(" + b.getText().toString() + ')';
        parsedFunction += a.getText() + "<sup><small>" + b.getText() + "</small></sup>";
        func.setText(Html.fromHtml(parsedFunction));
        dialog.dismiss();
    }

    public void putX(View v){
        function += 'x';
        parsedFunction += "x";
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putDecimalPoint(View v){
        function += '.';
        parsedFunction += ".";
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putAdd(View v){
        function += '+';
        parsedFunction += "+";
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putSub(View v) {
        function += '-';
        parsedFunction = "-";
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putMul(View v){
        function += '*';
        parsedFunction += "·";
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putDiv(View v){
        function += '/';
        parsedFunction += "/";
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putNum(int num){
        function += num;
        parsedFunction += num;
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putSin(){
        function += "§";
        parsedFunction += MathFont.s + MathFont.i + MathFont.n;
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putCos(){
        function += "±";
        parsedFunction += MathFont.c + MathFont.o + MathFont.s;
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putTan(){
        function += "#";
        parsedFunction += MathFont.t + MathFont.a + MathFont.n;
        func.setText(Html.fromHtml(parsedFunction));
    }

    public void putLog(){
        function += "(" + a.getText().toString() + ")!(" + b.getText().toString() + ')';
        parsedFunction += MathFont.l +MathFont.o + MathFont.g + "<sub><small>" + a.getText() + "</small></sub>" + b.getText();
        func.setText(Html.fromHtml(parsedFunction));
        dialog.dismiss();
    }

}