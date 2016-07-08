package com.luxoft.training.dev018.androidexamples.radioexample;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Utils {
    public static String getFormattedTime(int hours, int minutes) {
        NumberFormat format = new DecimalFormat("00");
        return format.format(hours) + ":" + format.format(minutes);
    }
}
