package com.asesorhub.utils;

public class ControllerUtils {

    private ControllerUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValidPagination(int page, int size) {
        return page >= 0 && size > 0;
    }

}