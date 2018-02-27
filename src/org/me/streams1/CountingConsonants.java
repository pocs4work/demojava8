package org.me.streams1;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

public class CountingConsonants {

    public static void main(String[] args) {
        countingConsonants();
        countingConsonantsFunInt();
        countingConsonantsStepByStepWithOptional();
        countingConsonantsMethRef();
    }

    // list of cities
    static List<String> citiesList = Arrays.asList("Lausanne", "Milan", "Geneva", "Tokyo", "Sydney");

    // lambdas counting # of consonants in all the elements of the list
    public static void countingConsonants() {

        Integer out = citiesList.stream()
                .map((p) -> p.replaceAll("[aeiou]", "")) // kill vowels (lower case)
                .map((a) -> a.length()) // length of list's elements without vowels
                .reduce((a, b) -> a + b) // sum each previously computed lengths
                .get();

        System.out.println("# of consonants: " + out + " (with countingConsonants method)");
    }

    // using @FunctionalInterfaces
    public static void countingConsonantsFunInt() {
        Integer out = citiesList.stream()
                .map(devowelization)
                .map(measureLength)
                .reduce(aggregator)
                .get();

        System.out.println("# of consonants: " + out + " (with countingConsonantsFunInt method)");
    }

    // @FunctionalInterfaces
    static BinaryOperator<Integer> aggregator = (a, b) -> a + b;
    static Function<String, Integer> measureLength = (a) -> a.length();
    static Function<String, String> devowelization = (p) -> p.replaceAll("[aeiou]", "");

    // streams step by step with Optional
    public static void countingConsonantsStepByStepWithOptional() {

        Stream<String> stream = citiesList.stream();
        Stream<String> devowelized = stream.map(devowelization);
        Stream<Integer> devowelizedLength = devowelized.map(measureLength);
        Optional<Integer> reduction = devowelizedLength.reduce(aggregator);
        Integer out = reduction.get();

        System.out.println("# of consonants: " + out + " (with countingConsonantsStepByStepWithOptional method)");
    }

    // with method references
    public static void countingConsonantsMethRef() {
        Integer out = citiesList.stream()
                .map(workingMethods::devowelization)
                .map(workingMethods::measureLength)
                .reduce(workingMethods::aggregator)
                .get();

        System.out.println("# of consonants: " + out + " (with countingConsonantsMethRef method)");
    }

    // referenceable methods
    public static class workingMethods {

        public static Integer aggregator(Integer a, Integer b) {
            return a + b;
        };

        public static Integer measureLength(String s) {
            return s.length();
        };

        public static String devowelization(String s) {
            return s.replaceAll("[aeiou]", "");
        };
    }

}
