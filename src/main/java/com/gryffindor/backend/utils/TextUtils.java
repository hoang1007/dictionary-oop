package com.gryffindor.backend.utils;

public class TextUtils {
    public static String format(String arg) {
        return arg.trim().replace('_', ' ');
    }

    public static String[] format(String[] args) {
        for (String arg : args) {
            arg = format(arg);
        }

        return args;
    }
}
