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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class addFunctionActivity extends AppCompatActivity {

    Button dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_function);

        Dialog dg = new Dialog(this);
        dg.setContentView(R.layout.dialog);
        TextView txt = dg.findViewById(R.id.tx);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dg.dismiss();
            }
        });

        dialog = findViewById(R.id.diag);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dg.show();
            }
        });

        //intent
        Intent in = getIntent();
        Intent addFunc = new Intent();

        //user input
        EditText func = findViewById(R.id.func);
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFunc.putExtra("func",func.getText().toString());
                setResult(RESULT_OK, addFunc);
                finish();
            }
        });


    }



}