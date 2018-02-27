package org.me.streams2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayWithLambdasAndStreams {

    public static void main(String[] args) {

        // list of bills
        List<Bill> bills = new ArrayList<>();

        bills.add(new Bill("PL-184866932-L", 4599.59, "ASFG"));
        bills.add(new Bill("RS-254676546-M", 567.79, "CPI"));
        bills.add(new Bill("KW-207548758-B", 4500.70, "CERN"));
        bills.add(new Bill("PP-207743787-M", 367.76, "UBS"));

        // sum of amounts < 1000
        Optional<Double> result = bills.stream()
                .map(b -> b.getAmount())
                .filter(d -> d < 1000)
                .reduce(Double::sum);

        System.out.println("Total: " + result.get());

        System.out.println("------------");

        // bills with amounts > 1000
        String resultConcat = bills.stream()
                .filter(b -> b.getAmount() > 1000)
                .parallel()
                .map(Bill::getReference)
                .collect(Collectors.joining(":"));

        System.out.println("Bills with amount > 1000:  " + resultConcat);

        System.out.println("------------");

        // bills with amount > 4550
        bills.parallelStream()
                .filter(b -> b.getAmount() > 4550)
                .sequential()
                .map(Bill::getReference)
                .forEach(System.out::println);
    }

}
