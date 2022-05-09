package com.example.hw2704_bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result;
    Button btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, btnNum0, btnAdd, btnSub, btnMul, btnDiv, btnCompute, btnCE, btnC, btnBS;
    STATE state;
    String curNum, prevNum;
    Character operator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Xac dinh vi tri cac view
        result = (TextView) findViewById(R.id.calResult);
        btnNum0 = (Button) findViewById(R.id.btnNum0);
        btnNum1 = (Button) findViewById(R.id.btnNum1);
        btnNum2 = (Button) findViewById(R.id.btnNum2);
        btnNum3 = (Button) findViewById(R.id.btnNum3);
        btnNum4 = (Button) findViewById(R.id.btnNum4);
        btnNum5 = (Button) findViewById(R.id.btnNum5);
        btnNum6 = (Button) findViewById(R.id.btnNum6);
        btnNum7 = (Button) findViewById(R.id.btnNum7);
        btnNum8 = (Button) findViewById(R.id.btnNum8);
        btnNum9 = (Button) findViewById(R.id.btnNum9);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMulti);
        btnDiv = (Button) findViewById(R.id.btnDivide);
        btnCompute = (Button) findViewById(R.id.btnCompute);
        btnC = (Button) findViewById(R.id.btnC);
        btnCE = (Button) findViewById(R.id.btnCE);
        btnBS = (Button) findViewById(R.id.btnBS);
        // Them event listener
        btnNum0.setOnClickListener(this);
        btnNum1.setOnClickListener(this);
        btnNum2.setOnClickListener(this);
        btnNum3.setOnClickListener(this);
        btnNum4.setOnClickListener(this);
        btnNum5.setOnClickListener(this);
        btnNum6.setOnClickListener(this);
        btnNum7.setOnClickListener(this);
        btnNum8.setOnClickListener(this);
        btnNum9.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnSub.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnCompute.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnCE.setOnClickListener(this);
        btnBS.setOnClickListener(this);
        // Khoi tao gia tri ban dau
        result.setText("0");
        state = STATE.BEFORE_COMPUTE;
        curNum = "0";
        prevNum = "0";
        operator = ' ';
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // CE va C
            case R.id.btnC:
                result.setText("0");
                state = STATE.BEFORE_COMPUTE;
                curNum = "0";
                prevNum = "";
                operator = ' ';
                //updateDisplay();
                break;
            case R.id.btnCE:
                curNum = "0";
                state = STATE.NUMBER;
                //updateDisplay();
                break;

            // number
            case R.id.btnNum0:
            case R.id.btnNum1:
            case R.id.btnNum2:
            case R.id.btnNum3:
            case R.id.btnNum4:
            case R.id.btnNum5:
            case R.id.btnNum6:
            case R.id.btnNum7:
            case R.id.btnNum8:
            case R.id.btnNum9:
                if (state == STATE.BEFORE_COMPUTE) {
                    prevNum = "";
                    operator = ' ';
                    curNum = "0";
                    curNum = curNum + v.getContentDescription();
                } else if (state == STATE.OPERATOR) {
                    curNum = curNum + v.getContentDescription();
                } else {
                    curNum = curNum + v.getContentDescription();
                }
                curNum = beautifyNumber(curNum);
                prevNum = beautifyNumber(prevNum);
                state = STATE.NUMBER;
                //updateDisplay();
                break;
            // Operator
            case R.id.btnAdd:
            case R.id.btnSub:
            case R.id.btnMulti:
            case R.id.btnDivide:
                if (state == STATE.BEFORE_COMPUTE) {
                    prevNum = curNum;
                    operator = v.getContentDescription().charAt(0);
                    curNum = "0";
                } else if (state == STATE.OPERATOR) {
                    operator = v.getContentDescription().charAt(0);
                } else {
                    // Chua xet den truong hop dang co tinh toan xay ra
                    if (operator == ' ') {
                        prevNum = curNum;
                        curNum = "0";
                    } else {
                        int prevNumber = Integer.parseInt(prevNum);
                        int curNumber = Integer.parseInt(curNum);
                        int tmp = curNumber;
                        switch (operator) {
                            case '+':
                                tmp = prevNumber + curNumber;
                                break;
                            case '-':
                                tmp = prevNumber - curNumber;
                                break;
                            case '*':
                                tmp = prevNumber * curNumber;
                                break;
                            case '/':
                                if (curNumber != 0) {
                                    tmp = prevNumber / curNumber;
                                    break;
                                } else {
                                    return;
                                }
                        }
                        prevNum = String.valueOf(tmp);
                        curNum = "0";
                    }

                    operator = v.getContentDescription().charAt(0);
                }
                state = STATE.OPERATOR;
                //updateDisplay();
                break;
            // Delete
            case R.id.btnBS:
                if (state == STATE.BEFORE_COMPUTE || state == STATE.OPERATOR)
                    return;
                if (curNum.length() <= 1)
                    curNum = "0";
                else {
                    curNum = curNum.substring(0, curNum.length() - 1);
                }
                state = STATE.NUMBER;
                break;
            // Compute
            case R.id.btnCompute:
                if (state == STATE.BEFORE_COMPUTE) {
                    if (operator == ' ') {
                        return;
                    } else {
                        int prevNumber = Integer.parseInt(prevNum);
                        int curNumber = Integer.parseInt(curNum);
                        int tmp = curNumber;
                        switch (operator) {
                            case '+':
                                tmp = prevNumber + curNumber;
                                break;
                            case '-':
                                tmp = prevNumber - curNumber;
                                break;
                            case '*':
                                tmp = prevNumber * curNumber;
                                break;
                            case '/':
                                if (curNumber != 0) {
                                    tmp = prevNumber / curNumber;
                                    break;
                                } else {
                                    return;
                                }
                        }
                        result.setText(prevNumber + " " + operator + " " + curNumber + " =\n" + tmp);
                        prevNum = String.valueOf(curNumber);
                        curNum = String.valueOf(tmp);
                        state = STATE.BEFORE_COMPUTE;
                        return;

                    }
                } else if (state == STATE.OPERATOR) {
                    return;
                } else {
                    if (operator == ' ') {
                        result.setText(curNum + " =\n" + curNum);
                        state = STATE.BEFORE_COMPUTE;
                        return;
                    } else {
                        int prevNumber = Integer.parseInt(prevNum);
                        int curNumber = Integer.parseInt(curNum);
                        int tmp = curNumber;
                        switch (operator) {
                            case '+':
                                tmp = prevNumber + curNumber;
                                break;
                            case '-':
                                tmp = prevNumber - curNumber;
                                break;
                            case '*':
                                tmp = prevNumber * curNumber;
                                break;
                            case '/':
                                if (curNumber != 0) {
                                    tmp = prevNumber / curNumber;
                                    break;
                                } else {
                                    return;
                                }
                        }
                        result.setText(prevNumber + " " + operator + " " + curNumber + " =\n" + tmp);
                        prevNum = String.valueOf(curNumber);
                        curNum = String.valueOf(tmp);
                        state = STATE.BEFORE_COMPUTE;
                        return;
                    }
                }
        }
        updateDisplay();
    }
    public void updateDisplay() {
        System.out.println(curNum + " " + prevNum);
        try {
//            curNum = String.valueOf(Integer.parseInt(curNum));
//            prevNum = String.valueOf(Integer.parseInt(prevNum));
            if (prevNum.trim().equals(""))
                result.setText(curNum);
            else
                result.setText(prevNum + " " + operator + "\n" + curNum);
        } catch (Exception e) {
            return;
        }

    }
    public String beautifyNumber(String number) {
        String result = number;
        if (result.length() <= 1)
            return result;
        else {
            while (result.charAt(0) == '0') {
                result = result.substring(1);
                if (result.length() <= 1)
                    break;
            }
            return result;
        }
    }
}