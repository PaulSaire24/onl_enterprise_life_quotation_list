package com.bbva.rbvd.lib.r406.impl.utils;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ConvertUtils {

    private ConvertUtils(){}


    public static String convertStringToUpperAndLowerCase(String value){
        return value.toUpperCase().charAt(0) + value.substring(1).toUpperCase();
    }

    public static boolean stringIsNullOrEmpty(String value){
        return value == null || value.isEmpty() || " ".equals(value);
    }

    public static LocalDate convertStringDateToLocalDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static Date convertLocalDateToDate(LocalDate date){
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
