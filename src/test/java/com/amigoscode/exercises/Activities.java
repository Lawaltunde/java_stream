package com.amigoscode.exercises;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;


public class Activities {
    public record Transaction(
            int id,
            double amount,
            String type,
            String date,
            Customer customer
    ) {
    }

    public record Customer(
            int id,
            String name,
            String email
    ) {
    }

    @Test
    void exerciseTwo() {
        // using imperative approach
        List<List<String>> arrayListOfNames = List.of(
                List.of("Mariam", "Alex", "Ismail"),
                List.of("John", "Alesha", "Andre"),
                List.of("Susy", "Ali")
        );
        List<String> names = new ArrayList<>();
        arrayListOfNames.forEach(names::addAll);
        System.out.println(names);
    }

    @Test
    void exerciseOne() {
        // to do: take data -> map to person -> filter teenagers -> find if any email ends in gov.uk
        String data = """ 
                    16,Fanchette,Williamson,fwilliamson0@github.com,F
                    26,Aleksandr,Matts,amatts1@webs.com,M
                    3,Maurie,Cordero,mcordero2@google.co.jp,M
                    45,Donnajean,Crowson,dcrowson3@google.com.hk,F
                    5,Ricardo,Gofton,rgofton4@nytimes.com,M
                    65,Gabie,Tregenna,gtregenna5@guardian.co.uk,F
                    37,Marjorie,Blumsom,mblumsom6@joomla.org,F
                    18,Lester,Huyghe,lhuyghe7@jugem.jp,M
                    39,Merrily,Stangoe,mstangoe8@tiny.cc,F
                    10,Reider,Karel,rkarel9@github.io,M
                    11,Dory,Jolliff,djolliffa@wufoo.com,F
                    32,Homerus,Averay,haverayb@skyrock.com,M
                    13,Alyda,Muglestone,amuglestonec@is.gd,F
                    14,Pinchas,Louca,ploucad@google.es,M
                    11,Cherin,Eltringham,celtringhame@parallels.com,F
                    2,Mufi,Rothert,mrothertf@dropbox.com,F
                    17,Jordana,Everex,jeverexg@ucla.edu,F
                    18,Belle,Rother,brotherh@gov.uk,F
                    19,Clevie,Sifflett,csiffletti@furl.net,M
                    20,Gretchen,Abell,gabellj@1688.com,F
                """;
        List<Person> fromUk = data.lines().map(e -> e.split(",")).map(p ->
                        new Person(Integer.parseInt(p[0].trim()), p[1].trim(), p[2].trim(), p[3].trim(), Gender.valueOf(p[4].trim()))).
                filter(l -> l.age() >= 13 && l.age() <= 19).
                filter(m -> m.email.endsWith("gov.uk")).toList();

        //fromUk.forEach(System.out::println);
    }

    record Person(int age, String firstName, String lastName, String email, Gender gender) {
    }

    @Test
    void name() {
        List<Transaction> transactions = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            transactions.add(new Transaction(
                    i,
                    Math.random() * 1000, // Random amount between 0 and 1000
                    i % 2 == 0 ? "CREDIT" : "DEBIT", // Alternate between CREDIT and DEBIT
                    "2024-" + String.format("%02d", (i % 12) + 1) + "-" + String.format("%02d", (i % 28) + 1), // Random date
                    new Customer(i, "Customer" + i, "customer" + i + "@example.com")
            ));
        }
        //1- Find all credit Transaction
        List<Transaction> credit = transactions.stream().filter(t -> t.type().equals("CREDIT")).toList();
        credit.forEach(System.out::println);

        // 2- Calculate the total amount of all the transaction
        double sum = transactions.stream().mapToDouble(t -> t.amount).sum();
        System.out.println(BigDecimal.valueOf(sum));

        // 3- find transaction with the highest amount
        OptionalDouble max = transactions.stream().mapToDouble(t -> t.amount).max();
        System.out.println(max.orElse(0));

        //4- count the number of DEBIT and CREDIT transactions
        transactions.stream().collect(Collectors.groupingBy(t -> t.type)).forEach((type, t) -> {
            System.out.println(type);
            System.out.println((long) t.size());
        });
        //5- Retrieve a list of distinct customers from the transactions
        List<Transaction> distinctTransactions = transactions.stream().distinct().toList();
        distinctTransactions.forEach(System.out::println);

        //6- Find the transactions above a threshold
        List<Transaction> tansactionsAboveThreeHundred = transactions.stream().filter(t -> t.amount > 300).toList();
        tansactionsAboveThreeHundred.forEach(System.out::println);
        //7- Group transaction by customer ID
        Map<Integer, List<Transaction>> transactionGroupByID = transactions.stream().collect(Collectors.groupingBy(s -> s.customer().id));
        transactionGroupByID.forEach((s, t) -> {
            System.out.println(s);
            t.forEach(System.out::println);
        });

        //8- First Transaction in the database
        System.out.println("-----First Transaction------");
        transactions.stream().limit(1).forEach(System.out::println);

        // sort all transaction by amount in ascending order
        List<Transaction> sortedTrasaction = transactions.stream().sorted(Comparator.comparing(t -> t.amount)).toList();
        sortedTrasaction.forEach(System.out::println);
    }
}
enum Gender {
    M,
    F
}

