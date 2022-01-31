package com.example.karpgraphing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addFunction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_function);

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