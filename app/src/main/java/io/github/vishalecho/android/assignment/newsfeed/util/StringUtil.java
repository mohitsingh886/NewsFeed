package io.github.vishalecho.android.assignment.newsfeed.util;

public final class StringUtil {

    public static boolean checkIfEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean checkIfNotEmpty(String str) {
        return !checkIfEmpty(str);
    }
}
