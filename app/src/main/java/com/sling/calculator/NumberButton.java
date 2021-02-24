package com.sling.calculator;

import android.content.Context;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatButton;

public class NumberButton extends AppCompatButton {
        NumberButton(Context context, NumberButtonData data, OnClickListener onClickListener){
            super(context);
            GridLayout.LayoutParams gridParams = new GridLayout.LayoutParams();
            gridParams.columnSpec = GridLayout.spec(data.col,data.colSpan,1);
            gridParams.rowSpec = GridLayout.spec(data.row, 1,1);
            setLayoutParams(gridParams);
            setText(data.textValue);
            setOnClickListener(onClickListener);
        }


}
