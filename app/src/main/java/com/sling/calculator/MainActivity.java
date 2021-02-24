package com.sling.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//Button Information for grid Layout:
    private ArrayList<NumberButtonData> numberButtonData = new ArrayList<NumberButtonData>(){
        {
            add(new NumberButtonData("7", 1,0,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData("8", 1,1,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData("9", 1,2,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData(" / ", 1,3,1, NumberButtonData.ButtonType.OPERATOR));
            add(new NumberButtonData("4", 2,0,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData("5", 2,1,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData("6", 2,2,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData(" * ", 2,3,1, NumberButtonData.ButtonType.OPERATOR));
            add(new NumberButtonData("1", 3,0,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData("2", 3,1,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData("3", 3,2,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData(" - ", 3,3,1, NumberButtonData.ButtonType.OPERATOR));
            add(new NumberButtonData("0", 4,1,2, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData(".", 4,0,1, NumberButtonData.ButtonType.INPUT));
            add(new NumberButtonData(" + ", 4,3,1, NumberButtonData.ButtonType.OPERATOR));
            add(new NumberButtonData("Clear", 0,0,4, NumberButtonData.ButtonType.ACTION));
            add(new NumberButtonData("=", 5,0,4, NumberButtonData.ButtonType.ACTION));


        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;


        GridLayout mainGrid = new GridLayout(this);
        mainGrid.setColumnCount(4);
        mainGrid.setLayoutParams(params);

        final AppCompatTextView text = new AppCompatTextView(this);
        text.setBackgroundColor(ContextCompat.getColor(this, R.color.panelColor));
        text.setTextColor(ContextCompat.getColor(this, R.color.textColor));
        text.setLayoutParams(params);
        text.setTextSize(1,24);
        text.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        mainLayout.addView(text);



//Generate Buttons:
        for(final NumberButtonData data:numberButtonData ){
            NumberButton button = new NumberButton(this, data,
                     new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Clear any errors in previous calculations.
                            if(text.getText().toString().equals("NaN") || text.getText().toString().contains("infinity")){
                                text.setText("");
                            }
                            if(data.type == NumberButtonData.ButtonType.INPUT){
                            String temp = text.getText().toString();
                            text.setText(temp + data.textValue);}

                            if(data.type == NumberButtonData.ButtonType.OPERATOR){
                                if(text.getText()!= "" && !text.getText().toString().endsWith(" ")){
                                    String temp = text.getText().toString();
                                    text.setText(temp + data.textValue);
                                }
                            }

                            if(data.textValue == "Clear"){
                                text.setText("");
                            }

                            if(data.textValue == "="){
                                double number = evaluate(text.getText().toString());
                                text.setText(String.valueOf(number));
                            }
                        }
                     }
                );
            if(data.type == NumberButtonData.ButtonType.INPUT){
                button.setBackgroundColor(ContextCompat.getColor(this , R.color.colorPrimary));
            }
            if(data.type == NumberButtonData.ButtonType.ACTION){
                button.setBackgroundColor(ContextCompat.getColor(this , R.color.colorAccent));
            }
            if (data.type == NumberButtonData.ButtonType.OPERATOR){
                button.setBackgroundColor(ContextCompat.getColor(this , R.color.colorPrimaryDark));
            }
            button.setTextSize(24);

            mainGrid.addView(button);
        }



        mainLayout.addView(mainGrid);

        setContentView(mainLayout);
    }


    //Will parse the given input into an expression and then solve it.
    //@param expression: a string expression with double numbers and operators. Note: operators are surrounded by " ".
    public double evaluate (String expression){
        if(expression.equals("")) return 0;
        String[] splitExpression = expression.split(" ");
        if(splitExpression.length == 1 || splitExpression.length == 2) return Double.parseDouble(splitExpression[0]);

        double value = Double.parseDouble(splitExpression[0]);
        String operator = "";
        for(int i = 1; i< splitExpression.length; i++){
            if(i%2 == 1){
                operator = splitExpression[i];
            }
            else{
                if(operator.equals("+")){
                    value = value + Double.parseDouble(splitExpression[i]);
                }
                if(operator.equals("-")){
                    value = value - Double.parseDouble(splitExpression[i]);
                }
                if(operator.equals("*")){
                    value = value * Double.parseDouble(splitExpression[i]);
                }
                if(operator.equals("/") && Double.parseDouble(splitExpression[i])!=0 ){
                    value = value / Double.parseDouble(splitExpression[i]);
                }
                if(operator.equals("/") && Double.parseDouble(splitExpression[i])==0 ){
                    return Double.NaN;
                }
            }
        }
        return value;
    }
}
