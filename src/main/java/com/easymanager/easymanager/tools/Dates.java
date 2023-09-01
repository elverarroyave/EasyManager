package com.easymanager.easymanager.tools;

import java.time.LocalDateTime;

public class Dates {
    public static LocalDateTime convertDateStringToLocalDateTime(String date){
        String[] arrayDate = date.split("-");
        return LocalDateTime.of(
                Integer.parseInt(arrayDate[0]),
                Integer.parseInt(arrayDate[1]),
                Integer.parseInt(arrayDate[2]),
                00,00);
    }
}
