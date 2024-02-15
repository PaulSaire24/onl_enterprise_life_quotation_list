package com.bbva.rbvd.lib.r406.impl.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConvertUtils {

    public static String convertStringToUpperAndLowerCase(String value){
        return value.toUpperCase().charAt(0) + value.substring(1).toUpperCase();
    }

    public static boolean stringIsNullOrEmpty(String value){
        return value == null || value.isEmpty() || " ".equals(value);
    }

    public static LocalDateTime convertStringDateToLocalDateTime(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);
    }

    public static LocalDate convertStringDateToLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

}
