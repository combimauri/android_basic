package com.possiblelabs.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    private final static int BUTTONS_SIZE = 11;
    private Button[] btnNumbers;

    private TextView txtResult;
    private ImageButton btnPlus;
    private ImageButton btnMinus;
    private ImageButton btnDivide;
    private ImageButton btnMultiply;
    private ImageButton btnEquals;
    private String operator;
    private boolean resultField = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNumbers = new Button[BUTTONS_SIZE];
        txtResult = (TextView) findViewById(R.id.txt_result);
        btnPlus = (ImageButton) findViewById(R.id.btn_plus);
        btnMinus = (ImageButton) findViewById(R.id.btn_minus);
        btnDivide = (ImageButton) findViewById(R.id.btn_divide);
        btnMultiply = (ImageButton) findViewById(R.id.btn_multiply);
        btnEquals = (ImageButton) findViewById(R.id.btn_equals);

        btnNumbers[0] = (Button) findViewById(R.id.btn_0);
        btnNumbers[1] = (Button) findViewById(R.id.btn_1);
        btnNumbers[2] = (Button) findViewById(R.id.btn_2);
        btnNumbers[3] = (Button) findViewById(R.id.btn_3);
        btnNumbers[4] = (Button) findViewById(R.id.btn_4);
        btnNumbers[5] = (Button) findViewById(R.id.btn_5);
        btnNumbers[6] = (Button) findViewById(R.id.btn_6);
        btnNumbers[7] = (Button) findViewById(R.id.btn_7);
        btnNumbers[8] = (Button) findViewById(R.id.btn_8);
        btnNumbers[9] = (Button) findViewById(R.id.btn_9);
        btnNumbers[10] = (Button) findViewById(R.id.btn_dot);

        for (Button btn : btnNumbers) {
            btn.setOnClickListener(this);
        }

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "+";
                txtResult.append(operator);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "-";
                txtResult.append(operator);
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "/";
                txtResult.append(operator);
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operator = "x";
                txtResult.append(operator);
            }
        });

        btnEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExpressionParser parser = new ExpressionParser();
                float result = parser.parseExpression(txtResult.getText().toString()).evaluate();
                txtResult.setText(result + "");
                resultField = true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        Button pressed = (Button) view;
        checkResultField();
        for (Button btn : btnNumbers) {
            if (pressed == btn) {
                String v = btn.getText().toString();
                txtResult.append(v);
            }
        }
    }

    private void checkResultField() {
        if (resultField == true) {
            resultField = false;
            txtResult.setText("");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
