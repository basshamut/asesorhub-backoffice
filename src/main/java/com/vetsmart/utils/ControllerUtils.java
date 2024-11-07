package com.vetsmart.utils;

public class ControllerUtils {
    public static boolean isValidPagination(int page, int size) {
        return page >= 0 && size > 0;
    }

}
