package com.gryffindor.backend.utils;

public class TextUtils {
    public static void format(String... args) {
        for (String arg : args) {
            arg.trim();
            arg.replace('_', ' ');
        }
    }
}
