package com.bip;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<Employee> employees = EmployeeProvider.getEmployees();
        System.out.printf("stream/parallelStream");
        IntConsumer intConsumePrint = System.out::println;
        employees.stream().mapToInt(Employee::getId).forEach(intConsumePrint);
        employees.parallelStream().mapToInt(Employee::getId).forEach(intConsumePrint);
        employees.parallelStream().mapToInt(Employee::getId).forEachOrdered(intConsumePrint);

        //terminal operation find whether data found  or not
        System.out.println("findAny/findFirst");
        OptionalInt any = employees.stream().mapToInt(Employee::getId).findAny();
        OptionalInt first = employees.stream().mapToInt(Employee::getId)
                .filter(e -> e > 50)
                .findFirst();

        any.ifPresent(intConsumePrint);
        first.ifPresent(intConsumePrint);

        System.out.println("anyMatch/allMatch/nonMatch");

        boolean anyMatch = employees.stream().anyMatch(e -> e.getName().equalsIgnoreCase("John Doe"));
        boolean allMatch = employees.stream().allMatch(e -> e.getName().equalsIgnoreCase("John Doe"));
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().equalsIgnoreCase("John Doe"));

        System.out.println(List.of(anyMatch, allMatch, noneMatch));

        //reduce
        System.out.println("Reduce");
        int sum = employees.stream().mapToInt(Employee::getId).sum();
        System.out.println("Sum :"+sum);

        int reduce = employees.stream().mapToInt(Employee::getId).reduce(10, (a, s) -> a + s);
        System.out.println("Reduce "+reduce);

        int iReduce = employees.parallelStream().mapToInt(Employee::getId).reduce(10, (a, s) -> a + s);
        System.out.println("Reduce parallel "+iReduce);

        //max/min
        System.out.println("Max/Min");
        employees.stream().mapToDouble(Employee::getSalary).max().ifPresent(System.out::println);
        employees.stream().mapToDouble(Employee::getSalary).min().ifPresent(System.out::println);

        Consumer<Employee> println = System.out::println;
        //count/distinct
        employees.stream().distinct().forEachOrdered(println);

        //concat
        Stream<Employee> firstStream = employees.stream();
        Stream<Employee> lastStream = employees.stream();

        System.out.println("Stream Concat()");
        String streamOfNames = Stream.concat(firstStream, lastStream)
                .map(Employee::getName)
                .collect(Collectors.joining(", "));
        System.out.println(streamOfNames);

        //flatMap
        System.out.println("flatMap");
        List<Stream<Integer>> lists = Arrays.asList(Arrays.asList(1, 2, 3).stream(), Arrays.asList(4, 5, 6).stream());
        lists.stream().flatMap(Function.identity()).forEach(System.out::print);

        System.out.println("\nflatMapXXX");
        lists = Arrays.asList(Arrays.asList(1, 2, 3).stream(),
                                Arrays.asList(4, 5, 6).stream());
        lists.stream()
                .flatMapToInt(stream -> stream.mapToInt(Integer::intValue))
                .forEach(System.out::print);

        //limit
        System.out.println("\nlimit");
        OptionalInt.of(employees.stream().mapToInt(Employee::getId)
                .limit(2).sum())
                .ifPresent(System.out::println);

        System.out.println("\nskip");
        OptionalInt.of(employees.stream().mapToInt(Employee::getId)
                        .skip(2)
                        .limit(3)
                        .sum())
                .ifPresent(System.out::println);

        System.out.println("peek");
        OptionalInt.of(employees.stream().mapToInt(Employee::getId)
                        .peek(System.out::println)
                        .skip(2)
                        .limit(3)
                        .sum())
                .ifPresent(System.out::println);

        //generate
        System.out.println("Generate");
        OptionalInt.of(Stream.generate(new AtomicInteger(1)::getAndIncrement)
                .limit(10)
                .mapToInt(Integer::intValue)
                .sum())
                .ifPresent(System.out::println);

        Stream.iterate(2, n -> n + 2)
                .limit(10).peek(System.out::println)
                .count();

        long count = Stream.of(1,2,3,4,5,6,7,8,9).count();
        System.out.println("Count -> "+ count);

        System.out.println("reduce");
        Stream.of(1,2,3,4,5,6,7,8,9).reduce((f, s) -> f + s).ifPresent(System.out::println);

        System.out.println("reduce with initial value");
        int i = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(10, (f, s) -> f + s).intValue();
        System.out.println(i);

        Stream.of(1).onClose(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("I am runnable");
                })
                .onClose(() -> System.out.println("I am runnable1"))
                .close();
    }
}
