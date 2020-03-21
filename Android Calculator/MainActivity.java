package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

/*///////////////////////////////////////////////
/                                               /
/               Calculator Project              /
/            Designed By: Arnav Nayak           /
/                 Oct. 8, 2019                  /
/                  Mr.Schiff                    /
/                                               /
///////////////////////////////////////////////*/



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView display;
    TextView degDisplay;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    Button button9;
    Button buttonPlus;
    Button buttonMinus;
    Button buttonMultiply;
    Button buttonDivide;
    Button buttonEquals;
    Button buttonC;
    boolean equalState = false;
    boolean degMode = false;
    //false : rad
    //true : deg

    Button buttonSin;
    Button buttonCos;
    Button buttonTan;
    Button buttonPi;
    Button buttonInverse;
    Button buttonSquared;
    Button buttonCubed;
    Button buttonE;
    Button buttonSroot;
    Button buttonCroot;
    Button buttonLn;
    Button buttonLog;
    Button buttonRad;
    Button buttonPercent;
    Button buttonRand;
    Button buttonSecond;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int orientation = this.getResources().getConfiguration().orientation;
        display = findViewById(R.id.textView);

        button0 = findViewById(R.id.button0);
        button0.setOnClickListener(this);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);
        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);
        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(this);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(this);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMinus.setOnClickListener(this);
        buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonMultiply.setOnClickListener(this);
        buttonDivide = findViewById(R.id.buttonSlash);
        buttonDivide.setOnClickListener(this);
        buttonEquals = findViewById(R.id.buttonEquals);
        buttonEquals.setOnClickListener(this);
        buttonC = findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);
        if(orientation == Configuration.ORIENTATION_LANDSCAPE) {
            degDisplay = findViewById(R.id.degDisplay);
            buttonSin = findViewById(R.id.buttonSin);
            buttonSin.setOnClickListener(this);
            buttonCos = findViewById(R.id.buttonCos);
            buttonCos.setOnClickListener(this);
            buttonTan = findViewById(R.id.buttonTan);
            buttonTan.setOnClickListener(this);
            buttonPi = findViewById(R.id.buttonPi);
            buttonPi.setOnClickListener(this);
            buttonInverse = findViewById(R.id.buttonInverse);
            buttonInverse.setOnClickListener(this);
            buttonSquared = findViewById(R.id.buttonSquared);
            buttonSquared.setOnClickListener(this);
            buttonSquared.setText(Html.fromHtml("X<sup>2</sup>"));
            buttonCubed = findViewById(R.id.buttonCubed);
            buttonCubed.setOnClickListener(this);
            buttonCubed.setText(Html.fromHtml("X<sup>3</sup>"));
            buttonE = findViewById(R.id.buttonE);
            buttonE.setOnClickListener(this);
            buttonSroot = findViewById(R.id.buttonSroot);
            buttonSroot.setOnClickListener(this);
            buttonSroot.setText(Html.fromHtml("<sup>2</sup>√x"));
            buttonCroot = findViewById(R.id.buttonCroot);
            buttonCroot.setOnClickListener(this);
            buttonCroot.setText(Html.fromHtml("<sup>3</sup>√x"));
            buttonLn = findViewById(R.id.buttonLn);
            buttonLn.setOnClickListener(this);
            buttonLog = findViewById(R.id.buttonLog);
            buttonLog.setOnClickListener(this);
            buttonRad = findViewById(R.id.buttonRad);
            buttonRad.setOnClickListener(this);
            buttonPercent = findViewById(R.id.buttonPercent);
            buttonPercent.setOnClickListener(this);
            buttonRand = findViewById(R.id.buttonRand);
            buttonRand.setOnClickListener(this);
            buttonSecond = findViewById(R.id.buttonSecond);
            buttonSecond.setOnClickListener(this);

        }

    }

    public void onClick(View view){

        switch(view.getId()){
            case R.id.button0:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("0");
                }
                break;
            case R.id.button1:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("1");
                }
                break;
            case R.id.button2:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("2");
                }
                break;
            case R.id.button3:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("3");
                }
                break;
            case R.id.button4:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("4");
                }
                break;
            case R.id.button5:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("5");
                }
                break;
            case R.id.button6:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("6");
                }
                break;
            case R.id.button7:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("7");
                }
                break;
            case R.id.button8:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("8");
                }
                break;
            case R.id.button9:
                if(equalState){
                    equalState = false;
                    display.setText("");
                }
                if(display.getText().toString().equals("0")){
                    display.setText("Nope!");
                    equalState = true;
                }
                else{
                    display.append("9");
                }
                break;
            case R.id.buttonC:
                display.setText("");
                equalState = false;
                break;
            case R.id.buttonMultiply:
                display.append("*");
                equalState = false;
                break;
            case R.id.buttonMinus:
                display.append("-");
                equalState = false;
                break;
            case R.id.buttonSlash:
                display.append("/");
                equalState = false;
                break;
            case R.id.buttonPlus:
                display.append("+");
                equalState = false;
                break;
            case R.id.buttonSin:
                equalState = true;
                boolean errorSin = false;
                CharSequence sinString = display.getText();
                if(sinString.toString().equals("") || sinString.toString().equals("Nope!")){
                    errorSin = true;
                }
                if(!errorSin) {
                    double currentSin = Double.parseDouble(sinString.toString());
                    double resultSin = 0.0;
                    if (degMode) {
                        resultSin = Math.sin(Math.toRadians(currentSin));
                    } else {
                        resultSin = Math.sin(currentSin);
                    }
                    display.setText(resultSin + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonCos:
                equalState = true;
                boolean errorCos = false;
                CharSequence cosString = display.getText();
                if(cosString.toString().equals("") || cosString.toString().equals("Nope!")){
                    errorCos = true;
                }
                if(!errorCos) {
                    double currentCos = Double.parseDouble(cosString.toString());
                    double resultCos = 0.0;
                    if (degMode) {
                        resultCos = Math.cos(Math.toRadians(currentCos));
                    } else {
                        resultCos = Math.cos(currentCos);
                    }
                    display.setText(resultCos + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonTan:
                equalState = true;
                boolean errorTan = false;
                CharSequence tanString = display.getText();
                if(tanString.toString().equals("") || tanString.toString().equals("Nope!")){
                    errorTan = true;
                }
                if(!errorTan) {
                    double currentTan = Double.parseDouble(tanString.toString());
                    double resultTan = 0.0;
                    if (degMode) {
                        resultTan = Math.tan(Math.toRadians(currentTan));
                    } else {
                        resultTan = Math.tan(currentTan);
                    }
                    display.setText(resultTan + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonPi:
                equalState = true;
                display.setText(Math.PI + "");
                break;
            case R.id.buttonInverse:
                equalState = true;
                boolean errorInverse = false;
                if(display.getText().toString().equals("") || display.getText().toString().equals("Nope!")){
                    errorInverse = true;
                }
                if(!errorInverse) {
                    double currentInverse = Double.parseDouble(display.getText().toString());
                    double resultInverse = (1 / currentInverse);
                    display.setText(resultInverse + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonSquared:
                equalState = true;
                boolean errorSquare = false;
                if(display.getText().toString().equals("") || display.getText().toString().equals("Nope!")){
                    errorSquare = true;
                }
                if(!errorSquare) {
                    double currentSquared = Double.parseDouble(display.getText().toString());
                    double resultSquared = currentSquared * currentSquared;
                    display.setText(resultSquared + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonCubed:
                equalState = true;
                boolean errorCubed = false;
                if(display.getText().toString().equals("") || display.getText().toString().equals("Nope!")){
                    errorCubed = true;
                }
                if(!errorCubed) {
                    double currentCubed = Double.parseDouble(display.getText().toString());
                    double resultCubed = currentCubed * currentCubed * currentCubed;
                    display.setText(resultCubed + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonE:
                equalState = true;
                display.setText(Math.E + "");
                break;
            case R.id.buttonSroot:
                equalState = true;
                boolean errorSroot = false;
                if(display.getText().toString().equals("") || display.getText().toString().equals("Nope!")){
                    errorSroot = true;
                }
                if(!errorSroot) {
                    double currentSroot = Double.parseDouble(display.getText().toString());
                    double resultSroot = Math.sqrt(currentSroot);
                    display.setText(resultSroot + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonCroot:
                equalState = true;
                boolean errorCroot = false;
                if(display.getText().toString().equals("") || display.getText().toString().equals("Nope!")){
                    errorCroot = true;
                }
                if(!errorCroot) {
                    double currentCroot = Double.parseDouble(display.getText().toString());
                    double resultCroot = Math.cbrt(currentCroot);
                    display.setText(resultCroot + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonLn:
                equalState = true;
                boolean errorLn = false;
                if(display.getText().toString().equals("") || display.getText().toString().equals("Nope!")){
                    errorLn = true;
                }
                if(!errorLn) {
                    double currentLn = Double.parseDouble(display.getText().toString());
                    double resultLn = Math.log(currentLn);
                    display.setText(resultLn + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonLog:
                equalState = true;
                boolean errorLog = false;
                if(display.getText().toString().equals("") || display.getText().toString().equals("Nope!")){
                    errorLog = true;
                }
                if(!errorLog) {
                    double currentLog = Double.parseDouble(display.getText().toString());
                    double resultLog = Math.log10(currentLog);
                    display.setText(resultLog + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonRad:
                if(buttonRad.getText().equals("Rad")){
                    buttonRad.setText("Deg");
                    degDisplay.setText("Rad");
                    degMode = false;
                }
                else{
                    buttonRad.setText("Rad");
                    degDisplay.setText("Deg");
                    degMode = true;
                }
                break;
            case R.id.buttonPercent:
                equalState = true;
                boolean errorPercent = false;
                if(display.getText().toString().equals("") || display.getText().toString().equals("Nope!")){
                    errorPercent = true;
                }
                if(!errorPercent) {
                    double currentPercent = Double.parseDouble(display.getText().toString());
                    double resultPercent = (currentPercent / 100.0);
                    display.setText(resultPercent + "");
                }
                else{
                    display.setText("Nope!");
                }
                break;
            case R.id.buttonRand:
                equalState = true;
                TableRow tblrow1 = findViewById(R.id.tblrow1);
                TableRow tblrow2 = findViewById(R.id.tblrow2);
                TableRow tblrow3 = findViewById(R.id.tblrow3);
                TableRow tblrow4 = findViewById(R.id.tblrow4);
                for(int i = 0; i < 8; i++){
                    Button button = (Button)tblrow1.getChildAt(i);
                    button.setBackgroundResource(R.drawable.button_round);
                    Drawable drawable =  button.getBackground();
                    if(i == 7){
                        drawable.setColorFilter(Color.parseColor("#FF9500"), PorterDuff.Mode.SRC_ATOP);
                    }
                    else{
                        drawable.setColorFilter(Color.parseColor("#505050"), PorterDuff.Mode.SRC_ATOP);
                    }
                }
                for(int i = 0; i < 8; i++){
                    Button button = (Button)tblrow2.getChildAt(i);
                    button.setBackgroundResource(R.drawable.button_round);
                    Drawable drawable =  button.getBackground();
                    if(i == 7){
                        drawable.setColorFilter(Color.parseColor("#FF9500"), PorterDuff.Mode.SRC_ATOP);
                    }
                    else{
                        drawable.setColorFilter(Color.parseColor("#505050"), PorterDuff.Mode.SRC_ATOP);
                    }
                }
                for(int i = 0; i < 8; i++){
                    Button button = (Button)tblrow3.getChildAt(i);
                    button.setBackgroundResource(R.drawable.button_round);
                    Drawable drawable =  button.getBackground();
                    if(i == 7){
                        drawable.setColorFilter(Color.parseColor("#FF9500"), PorterDuff.Mode.SRC_ATOP);
                    }
                    else{
                        drawable.setColorFilter(Color.parseColor("#505050"), PorterDuff.Mode.SRC_ATOP);
                    }
                }
                for(int i = 0; i < 8; i++){
                    Button button = (Button)tblrow4.getChildAt(i);
                    button.setBackgroundResource(R.drawable.button_round);
                    Drawable drawable =  button.getBackground();
                    if(i == 7){
                        drawable.setColorFilter(Color.parseColor("#FF9500"), PorterDuff.Mode.SRC_ATOP);
                    }
                    else{
                        drawable.setColorFilter(Color.parseColor("#505050"), PorterDuff.Mode.SRC_ATOP);
                    }
                }
                double resultRand = Math.random();
                display.setText(resultRand + "");
                break;
            case R.id.buttonSecond:
                tblrow1 = findViewById(R.id.tblrow1);
                tblrow2 = findViewById(R.id.tblrow2);
                tblrow3 = findViewById(R.id.tblrow3);
                tblrow4 = findViewById(R.id.tblrow4);
                for(int i = 0; i < 8; i++){
                    Button button = (Button)tblrow1.getChildAt(i);
                    button.setBackgroundResource(R.drawable.button_round);
                    Drawable drawable =  button.getBackground();
                    drawable.setColorFilter(getRandomColor(), PorterDuff.Mode.SRC_ATOP);
                }
                for(int i = 0; i < 8; i++){
                    Button button = (Button)tblrow2.getChildAt(i);
                    button.setBackgroundResource(R.drawable.button_round);
                    Drawable drawable =  button.getBackground();
                    drawable.setColorFilter(getRandomColor(), PorterDuff.Mode.SRC_ATOP);
                }
                for(int i = 0; i < 8; i++){
                    Button button = (Button)tblrow3.getChildAt(i);
                    button.setBackgroundResource(R.drawable.button_round);
                    Drawable drawable =  button.getBackground();
                    drawable.setColorFilter(getRandomColor(), PorterDuff.Mode.SRC_ATOP);
                }
                for(int i = 0; i < 8; i++){
                    Button button = (Button)tblrow4.getChildAt(i);
                    button.setBackgroundResource(R.drawable.button_round);
                    Drawable drawable =  button.getBackground();
                    drawable.setColorFilter(getRandomColor(), PorterDuff.Mode.SRC_ATOP);
                }
                break;
            case R.id.buttonEquals:
                CharSequence resultString = display.getText();
                StringTokenizer operationTokenizer = new StringTokenizer(resultString.toString(), "+-*/", true);
                ArrayList numbers = new ArrayList();
                boolean errorEqual = false;
                equalState = true;

                while(operationTokenizer.hasMoreTokens()){
                    numbers.add(operationTokenizer.nextToken());
                }

                if(numbers.get(numbers.size()-1).equals("*") || numbers.get(numbers.size()-1).equals("/") ||
                        numbers.get(numbers.size()-1).equals("+") ||numbers.get(numbers.size()-1).equals("-")){
                    errorEqual = true;
                }

                if(numbers.get(0).equals("-")){
                    numbers.remove(0);
                    numbers.set(0, Double.parseDouble(numbers.get(0).toString()) * -1);
                }

                if(numbers.get(0).equals("*") || numbers.get(0).equals("/") ||
                        numbers.get(0).equals("+")){
                    errorEqual = true;
                }

                for(int i = 0; i < numbers.size(); i++) {
                    if (numbers.get(i).equals("*") && !errorEqual) {
                        double first = Double.parseDouble(numbers.get(i - 1).toString());
                        double second = Double.parseDouble(numbers.get(i + 1).toString());
                        double result = first * second;
                        numbers.remove(i + 1);
                        numbers.remove(i);
                        numbers.remove(i - 1);
                        numbers.add(i - 1, result + "");
                        i = 0;
                    }
                }
                for(int i = 0; i < numbers.size(); i++) {
                    if (numbers.get(i).equals("/") && !errorEqual) {
                        double first = Double.parseDouble(numbers.get(i - 1).toString());
                        double second = Double.parseDouble(numbers.get(i + 1).toString());
                        if(second == 0.0){
                            errorEqual = true;
                        }
                        double result = first / second;
                        numbers.remove(i + 1);
                        numbers.remove(i);
                        numbers.remove(i - 1);
                        numbers.add(i - 1, result + "");
                        i = 0;
                    }
                }

                for(int i = 0; i < numbers.size(); i++) {
                    if ((numbers.get(i).equals("+") || (numbers.get(i).equals("-"))) && !errorEqual) {
                        double first = Double.parseDouble(numbers.get(i - 1).toString());
                        double second = Double.parseDouble(numbers.get(i + 1).toString());
                        double result = 0.0;
                        if(numbers.get(i).equals("-")){
                            result = first - second;
                        }
                        else{
                            result = first + second;
                        }
                        numbers.remove(i + 1);
                        numbers.remove(i);
                        numbers.remove(i - 1);
                        numbers.add(i - 1, result + "");
                        i = 0;
                    }
                }

                if(errorEqual){
                    display.setText("Nope!");
                }
                else{
                    if(Double.parseDouble(numbers.get(0).toString()) -
                            (int)(Double.parseDouble(numbers.get(0).toString())) == 0){
                        display.setText((int)(Double.parseDouble(numbers.get(0).toString())) + "");
                    }
                    else{
                        display.setText((numbers.get(0)) + "");
                    }
                }

                Log.d("result", numbers.get(0).toString());
                break;
        }
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256));
    }
}

