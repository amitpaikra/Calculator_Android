package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    StringBuilder evalStr = null;
    TextView expression_view = null;
    TextView result_view = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        evalStr = new StringBuilder();
        expression_view = findViewById(R.id.textView);
        result_view = findViewById(R.id.textView2);
    }

    public void add(View view){

        if(evalStr == null ) return;

        Button btn = (Button) findViewById(view.getId());
        if( btn.getId() == R.id.btn_clear ){
            evalStr = new StringBuilder();
            expression_view.setText(evalStr);
        }else if( btn.getId() == R.id.btn_back ){
            if( !(evalStr.length() > 0) ) return;
            evalStr.deleteCharAt(evalStr.length()-1);
            expression_view.setText(evalStr.toString());
            expression_view.setTextColor(Color.WHITE);

        }else if(btn.getId() == R.id.btn_equal){

            Double result = null;
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

            try {
                result = (double)engine.eval(evalStr.toString());
            } catch (ScriptException e) {
                e.printStackTrace();
                expression_view.setTextColor(Color.RED);
            }
            if( result != null ){
                result_view.setText(result.toString());
            }

        }else{
            evalStr.append(btn.getText());

            expression_view.setText(evalStr.toString());
        }

    }
}