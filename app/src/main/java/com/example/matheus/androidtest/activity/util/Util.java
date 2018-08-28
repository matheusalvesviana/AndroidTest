package com.example.matheus.androidtest.activity.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Util {

    public static int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,displayMetrics );
    }
}
