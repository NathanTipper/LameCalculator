package com.example.ntipper.lamecalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void processNumbers(View view) {
        EditText operand1Text = findViewById(R.id.operand1);
        EditText operand2Text = findViewById(R.id.operand2);
        EditText operatorText = findViewById(R.id.operator);
        int operand1 = 0;
        int operand2 = 0;
        char operator;
        try {
            operand1 = Integer.parseInt(operand1Text.getText().toString());
            operand2 = Integer.parseInt(operand2Text.getText().toString());
            operator = operatorText.getText().toString().charAt(0);
        }

        catch (NumberFormatException e) {
            throwToast("Values needed to be entered for the operands!");
            return;
        }

        catch (IndexOutOfBoundsException e) {
            throwToast("Please provide an operator!");
            return;
        }



        int result = 0;
        try {
            result = calculate(operand1, operand2, operator);
            EditText resultText = findViewById(R.id.result);
            CharSequence stringResult = String.valueOf(result);
            resultText.setText(stringResult, TextView.BufferType.EDITABLE);
        }

        catch(RuntimeException e) {
            throwToast(e.getMessage());
            return;
        }
    }

    public void clearValues(View view) {
        EditText operand1Text = findViewById(R.id.operand1);
        EditText operand2Text = findViewById(R.id.operand2);
        EditText operatorText = findViewById(R.id.operator);
        EditText resultText = findViewById(R.id.result);

        CharSequence blankString = "";
        operand1Text.setText(blankString, TextView.BufferType.EDITABLE);
        operand2Text.setText(blankString, TextView.BufferType.EDITABLE);
        operatorText.setText(blankString, TextView.BufferType.EDITABLE);
        resultText.setText(blankString, TextView.BufferType.EDITABLE);
    }

    private void throwToast(String message) {
        Context context = getApplicationContext();
        CharSequence text = message;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private int calculate(int a, int b, char operator) {

        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new RuntimeException("Cannot divide by zero!");
                else
                    return a / b;
            default:
                throw new RuntimeException("Please enter a valid operator (+, -, *, /)! ");
        }
    }
}
