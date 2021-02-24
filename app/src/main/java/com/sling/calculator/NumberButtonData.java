package com.sling.calculator;
//This Class contains all the needed information to generate a button for the calculator.
public class NumberButtonData {
    public String textValue;
    public int row;
    public int col;
    public int colSpan;
    public ButtonType type;


    public enum ButtonType{
        ACTION,
        INPUT,
        OPERATOR
    }

    public NumberButtonData(String textValue, int row, int col, int colSpan){
        this.textValue = textValue;
        this.row = row;
        this.col = col;
        this.colSpan = colSpan;
    }

    public NumberButtonData(String textValue, int row, int col, int colSpan, ButtonType type){
        this.textValue = textValue;
        this.row = row;
        this.col = col;
        this.colSpan = colSpan;
        this.type = type;
    }
}
