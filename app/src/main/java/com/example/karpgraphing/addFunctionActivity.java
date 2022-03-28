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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.karpgraphing.exp.Expression;

import java.util.Objects;

public class addFunctionActivity extends AppCompatActivity {
    Button log;
    Button pow;
    Button submitDialog;
    String function;
    String parsedFunction;
    Dialog dialog;
    EditText func;
    EditText a;
    EditText b;
    TextView txt;
    Expression expression;
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

        //pow function button onclick
        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                powOnClick();
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOnClick();
            }
        });

        //intent function to main activity
        Intent in = getIntent();
        Intent addFunc = new Intent();

        // submit function to main activity
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void updateExpression(){
        func.setText(Html.fromHtml(expression.toHtmlString()));
    }
}