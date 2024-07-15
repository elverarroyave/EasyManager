package com.easymanager.easymanager.tools;

public class DoubleUtil {

    public static double roundDecimal(double value) {
        return Double.parseDouble(String.format("%.2f", value));
    }
}
