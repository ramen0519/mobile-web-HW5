package com.example.fourbasicoptapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editNum1;
    private EditText editNum2;
    private TextView textResult;
    private final FourBasicOpt fourOpt = new FourBasicOpt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editNum1 = findViewById(R.id.editNum1);
        editNum2 = findViewById(R.id.editNum2);
        textResult = findViewById(R.id.textResult);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnSubtract = findViewById(R.id.btnSubtract);
        Button btnMultiply = findViewById(R.id.btnMultiply);
        Button btnDivide = findViewById(R.id.btnDivide);

        btnAdd.setOnClickListener(v -> calculate('+'));
        btnSubtract.setOnClickListener(v -> calculate('-'));
        btnMultiply.setOnClickListener(v -> calculate('*'));
        btnDivide.setOnClickListener(v -> calculate('/'));
    }

    private void calculate(char op) {
        String input1 = editNum1.getText().toString().trim();
        String input2 = editNum2.getText().toString().trim();
        if (input1.isEmpty() || input2.isEmpty()) {
            textResult.setText(R.string.error_empty);
            return;
        }

        double x;
        double y;
        try {
            x = Double.parseDouble(input1);
            y = Double.parseDouble(input2);
        } catch (NumberFormatException e) {
            textResult.setText(R.string.error_number);
            return;
        }

        double result;
        String symbol;
        switch (op) {
            case '+':
                result = fourOpt.add(x, y);
                symbol = getString(R.string.btn_add);
                break;
            case '-':
                result = fourOpt.subtract(x, y);
                symbol = getString(R.string.btn_subtract);
                break;
            case '*':
                result = fourOpt.multiply(x, y);
                symbol = getString(R.string.btn_multiply);
                break;
            default:
                result = fourOpt.divide(x, y);
                symbol = getString(R.string.btn_divide);
                break;
        }

        textResult.setText(getString(R.string.result_format,
                format(x), symbol, format(y), format(result)));
    }
    
    private String format(double value) {
        if (value == Math.rint(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
