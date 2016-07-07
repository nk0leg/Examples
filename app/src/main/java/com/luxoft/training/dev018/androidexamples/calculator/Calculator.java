package com.luxoft.training.dev018.androidexamples.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.luxoft.training.dev018.androidexamples.R;

public class Calculator extends AppCompatActivity implements View.OnClickListener {

    EditText etNum1;
    EditText etNum2;

    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;

    TextView tvResult;

    String operator = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // находим элементы
        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        tvResult = (TextView) findViewById(R.id.tvResult);

        // прописываем обработчик
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnDiv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        float num1 = 0;
        float num2 = 0;
        float result = 0;

        if (    TextUtils.isEmpty(etNum1.getText().toString()) ||
                TextUtils.isEmpty(etNum2.getText().toString())      ) {
            return;
        }
        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        switch (v.getId()) {
            case R.id.btnAdd:
                operator = "+";
                result = num1 + num2;
                break;
            case R.id.btnSub:
                operator = "-";
                result = num1 - num2;
                break;
            case R.id.btnMult:
                operator = "*";
                result = num1 * num2;
                break;
            case R.id.btnDiv:
                operator = "/";
                result = num1 / num2;
                break;
            default:
                break;
        }

        // формируем строку вывода
        tvResult.setText(num1 + " " + operator + " " + num2 + " = " + result);
    }
}

