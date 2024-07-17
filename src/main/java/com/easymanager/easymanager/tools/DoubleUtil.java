package com.easymanager.easymanager.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DoubleUtil {

    public static double roundDecimal(double value, int places) {
        return BigDecimal.valueOf(value)
                .setScale(places, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
