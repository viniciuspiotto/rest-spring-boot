package com.piotto.apigateway.utils;

public class MathUtils {

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        String number = str.replace(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }

    public static Double convertToDouble(String number) {
        if (number == null) {
            return null;
        }
        number = number.replace(",", ".");
        return Double.parseDouble(number);
    }

    public static boolean isZero(String number) {
        if (number == null) {
            return false;
        }
        return number.matches("[-+]?[1-9]*\\.?[1-9]+");
    }
}
