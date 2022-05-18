package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    StringBuilder evalStr = null;
    TextView view1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        evalStr = new StringBuilder();
        view1 = findViewById(R.id.textView);
    }

    public void add(View view){

        if(evalStr == null ) return;

        Button btn = (Button) findViewById(view.getId());

        if( btn.getId() == R.id.btn_clear ){
            evalStr = new StringBuilder();
        }else if( btn.getId() == R.id.btn_back ){
            if( !(evalStr.length() > 0) ) return;
            evalStr.deleteCharAt(evalStr.length()-1);
        }else if(evalStr != null){
            evalStr.append(btn.getText());
        }

        if(evalStr == null ) return;

        Toast.makeText(this, evalStr.toString(), Toast.LENGTH_SHORT).show();
        view1.setText(evalStr.toString());

    }
}