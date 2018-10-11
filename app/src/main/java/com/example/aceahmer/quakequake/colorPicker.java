package com.example.aceahmer.quakequake;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import java.text.DecimalFormat;

import static android.support.v4.content.ContextCompat.getColor;

public class colorPicker {
    public static int setColor(Context context,Double Magnitude) {
        int color;
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        switch ((int) Math.floor(Double.parseDouble(decimalFormat.format(Magnitude)))) {
            case 1:
                color=R.color.magnitude1;
                break;
            case 2:
                color=R.color.magnitude2;
                break;
            case 3:
                color=R.color.magnitude3;
                break;
            case 4:
                color=R.color.magnitude4;
                break;
            case 5:
                color=R.color.magnitude5;
                break;
            case 6:
                color=R.color.magnitude6;
                break;
            case 7:
                color=R.color.magnitude7;
                break;
            case 8:
                color=R.color.magnitude8;
                break;
            case 9:
                color=R.color.magnitude9;
                break;
           default:
                color=R.color.magnitude10plus;
                break;
        }
       return  ContextCompat.getColor(context,color);

    }
}
