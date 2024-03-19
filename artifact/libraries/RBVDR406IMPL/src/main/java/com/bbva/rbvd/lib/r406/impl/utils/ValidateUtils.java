package com.bbva.rbvd.lib.r406.impl.utils;

import java.util.Arrays;

public class ValidateUtils {

    private ValidateUtils(){}


    public static boolean stringIsNullOrEmpty(String value){
        return value == null || value.isEmpty();
    }

    public static boolean allValuesStringNotNullOrEmpty(String... values){
        return Arrays.stream(values).noneMatch(ValidateUtils::stringIsNullOrEmpty);
    }

}
