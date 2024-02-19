package com.bbva.rbvd.lib.r406.impl.utils;


import org.joda.time.LocalDate;

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
        return LocalDate.parse(date);
    }

    public static Date convertLocalDateToDate(LocalDate date){
        return date.toDateTimeAtStartOfDay().toDate();
    }

}
