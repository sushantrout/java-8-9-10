package com.bip;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsTest {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeProvider.getEmployees();
        System.out.printf("Collectors Example");

        int lSize = employees.stream().collect(Collectors.toList()).size();
        int sSize = employees.stream().collect(Collectors.toSet()).size();
        int cSize = employees.stream().collect(Collectors.toCollection(LinkedHashSet::new)).size();

        System.out.printf("Size : {%d}, {%d}, {%d} " , lSize , sSize , cSize);

        System.out.println("Averaging Int, Double, Long");
        Double average = employees.stream().collect(Collectors.averagingInt(Employee::getId));
        System.out.println("Average "+ average);

        System.out.println("CollectingAndThen");
        List<Integer> collect = employees.stream().map(Employee::getId).collect(
                Collectors.collectingAndThen(
                        Collectors.summingInt(Integer::intValue),
                        e -> Arrays.asList(e)
                )
        );

        Predicate<Integer> integerPredicate = a -> a <= 5;
        Set<Integer> collect1 = employees.stream()
                .map(Employee::getId)
                .collect(Collectors.collectingAndThen(
                    Collectors.filtering(integerPredicate, Collectors.toSet()),
                    e -> e
                ));

        System.out.println("Summing Int " + collect);
        System.out.println("Filtering " + collect1);

        System.out.println("Counting");
        var counting = employees.stream().collect(Collectors.counting());
        System.out.println(counting);

        counting = employees.stream().collect(Collectors.collectingAndThen(
                Collectors.filtering(e -> e.getId() < 5, Collectors.counting()),
                e -> e
        ));

        System.out.println(counting);


        System.out.println("groupingBy");
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment)).forEach((k, v) -> {
            System.out.println(k+ " => " +v);
        });

        System.out.println("\ngroupingBy(Function, Collectors)\n");
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                    Collectors.toCollection(LinkedHashSet::new)
                ))
                .forEach((k, v) -> { System.out.println(k+ " => " +v.stream()
                                .map(Employee::getName)
                        .collect(Collectors.joining(", "))); });

        System.out.println("\ngroupingBy(Function,mapFactory,  Collectors)\n");

        String name = "Sushant Kumar Rout".toLowerCase();
        Supplier<Map<Character, Long>> mapSupplier = LinkedHashMap::new;
        name.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(
                                Function.identity(), // Classification function
                                mapSupplier, // Supplier for custom map
                                Collectors.counting() // Downstream collector to count occurrences
                        ), map -> {
                            map.entrySet().removeIf(entry -> entry.getKey() == ' ');
                            return map;
                        }
                )).forEach((k, v) -> System.out.println(k + " => " + v));

        IntFunction<String> stringIntFunction = e -> (char) (e) + "";
        System.out.println("Joining");

        String joining1 = name.chars().mapToObj(stringIntFunction).collect(Collectors.joining());
        System.out.println(joining1);

        System.out.println("Joining(delimiter)");
        joining1 = name.chars().mapToObj(stringIntFunction).collect(Collectors.joining(","));
        System.out.println(joining1);

        System.out.println("Joining(delimiter, prefix, sufix)");
        joining1 = name.chars().mapToObj(stringIntFunction).collect(Collectors.joining(",", "[", "]"));
        System.out.println(joining1);

        System.out.println("\nmapping\n");
        joining1 = employees.stream()
                .collect(Collectors.mapping(Employee::getName, Collectors.joining(" ,")));

        System.out.println(joining1);

        System.out.println("max, min");
        System.out.println("maxBy");
        Comparator<Employee> comparing = Comparator.comparing(Employee::getId);
        employees.stream().collect(Collectors.maxBy(comparing))
                .ifPresent(System.out::println);

        System.out.println("minBy");
        employees.stream().collect(Collectors.minBy(comparing))
                .ifPresent(System.out::println);

        System.out.println("Max in the grouping by");
        employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(comparing),
                                maxEmployee -> maxEmployee.map(Employee::getId).orElse(0)
                        )
                )).forEach((k, v) -> System.out.println(k +" => " + v));



        //java 9
        List<List<List<String>>> records = Arrays.asList(
                Arrays.asList(
                        Arrays.asList("A", "B"),
                        Arrays.asList("C", "D")
                ),
                Arrays.asList(
                        Arrays.asList("E", "F"),
                        Arrays.asList("G", "H"),
                        Arrays.asList("I", "J")
                )
        );

        List<String> flattenedList = records.stream().collect(Collectors.flatMapping(
                record -> record.stream().flatMap(List::stream), // FlatMap the inner lists of hobbies
                Collectors.toList() // Collect the flat-mapped hobbies into a list
        ));

        System.out.println(flattenedList);

        employees.stream().collect(Collectors.teeing(
                Collectors.minBy(comparing),
                Collectors.maxBy(comparing),
                (a, b) -> Arrays.asList(a, b)
        )).stream().forEach(System.out::println);

    }
}
