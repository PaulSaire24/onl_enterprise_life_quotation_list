package com.bbva.rbvd.lib.r406.impl.utils;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ConvertUtils {

    private ConvertUtils(){}


    public static String convertStringToUpperAndLowerCase(String value){
        StringBuilder str = new StringBuilder();
        str.append(value.toUpperCase().charAt(0));
        str.append(value.toLowerCase().substring(1));

        return str.toString();
    }

    public static boolean stringIsNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    public static LocalDate convertStringDateToLocalDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static BigDecimal getBigDecimalValue(Object value){
        BigDecimal ret = null;
        if(value != null){
            if(value instanceof BigDecimal){
                ret = (BigDecimal) value;
            }else if(value instanceof String){
                ret = new BigDecimal((String) value);
            }else if(value instanceof Double){
                ret = BigDecimal.valueOf(((Double) value).doubleValue());
            }else if(value instanceof Integer){
                ret = BigDecimal.valueOf((Integer) value);
            }
        }

        return ret;
    }

    public static Date convertLocalDateToDate(LocalDate date){
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String convertObjectToString(Object obj){
        return JsonUtils.getInstance().serialization(obj);
    }

}
