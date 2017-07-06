package com.java_8_training.problems.lambdas;

import com.java_8_training.problems.lambdas.func_interface.TextFormatter;

import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class TitleFormatter implements TextFormatter {

    public static final Pattern spaces = Pattern.compile("\\s+");

    public String format(String title) {

        // This method breaks a string into words, makes each word lowercase, then capitalizes the first letter of each word
        return spaces.splitAsStream(title)
                     .map(String::toLowerCase)
                     .map(this::firstToUppercase)
                     .collect(joining(" "));
    }

    private String firstToUppercase(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    public static void main(String[] args) {
        TitleFormatter titleFormatter = new TitleFormatter();
        String format = titleFormatter.format("UN TITLU DE MILIOANE");
        System.out.println(format);
    }

}
