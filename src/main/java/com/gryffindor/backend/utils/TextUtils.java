package com.gryffindor.backend.utils;

public class TextUtils {
    public static String format(String arg) {
        return arg.replaceAll("[-_]", " ").trim();
    }

    public static String[] format(String[] args) {
        for (String arg : args) {
            arg = format(arg);
        }

        return args;
    }

    public static String empty() {
        return "";
    }
}
