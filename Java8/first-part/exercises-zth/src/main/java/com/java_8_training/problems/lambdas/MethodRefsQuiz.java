package com.java_8_training.problems.lambdas;

import com.java_8_training.problems.lambdas.func_interface.TextFormatter;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;


public class MethodRefsQuiz {

    public static void main(String[] args) {
        MethodRefsQuiz methodRefsQuiz = new MethodRefsQuiz();

        methodRefsQuiz.stringToInteger();
        methodRefsQuiz.contains();
        methodRefsQuiz.startsWithNumberQuiz();
        methodRefsQuiz.formatText();
    }

    public void stringToInteger(){
        String strNumber = "1310";

        //TODO: refactor to use a method reference
        Function<String, Integer> stringToInteger = (String s) -> Integer.parseInt(s);
        Integer intNumber = stringToInteger.apply(strNumber);

        System.out.println("Converted from " + strNumber + " as String to " + intNumber + " as Integer");
    }

    public void contains(){
        //TODO: refactor to use a method reference
        BiPredicate<List<String>, String> contains = (list, element) -> list.contains(element);

        List<String> list = Arrays.asList("who", "how", "why");
        String word = "who";
        boolean doesItContainIt = contains.test(list, word);

        System.out.println("List " + list + " contains " + word + "? " + doesItContainIt);
    }

    public void startsWithNumberQuiz() {
        //TODO: refactor to use a method reference
        Predicate<String> startsWithNumber = (string) -> startsWithNumber(string);

        String str = "1abc";
        boolean startsWithNr = startsWithNumber.test(str);

        System.out.println("Does String " + str + " start with a number? " + startsWithNr);
    }

    private boolean startsWithNumber(String string)
    {
        return Character.isDigit(string.charAt(0));
    }

    
    public void formatText() {

        TextFormatter formatter = new TitleFormatter();

        String filmTitle = "the force aWakens";

        //TODO: refactor to use a method reference
        Function<String, String> formatText = (String title) -> formatter.format(title);

        //TODO: use the formatText function to test the film title and print it to the console
    }


}
