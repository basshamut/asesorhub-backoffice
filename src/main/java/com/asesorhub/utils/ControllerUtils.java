package com.asesorhub.utils;

public class ControllerUtils {

    private ControllerUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValidPagination(String page, String size) {
        try {
            int pageInt = Integer.parseInt(page);
            int sizeInt = Integer.parseInt(size);
            return pageInt >= 0 && sizeInt > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}