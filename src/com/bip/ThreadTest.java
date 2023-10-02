package com.bip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ThreadTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable r = () -> System.out.println("Runnable");
        Callable c = () -> {
            System.out.println("Callable1");
            Thread.sleep(5000);
            return 1;
        };

        Callable c1 = () -> {
            System.out.println("Callable2");
            Thread.sleep(5000);
            return 2;
        };

        Thread thread = new Thread(r);
        thread.start();
        ExecutorService service = Executors.newFixedThreadPool(2);

        ArrayList<Callable<Integer>> tasks = new ArrayList<>();
        tasks.add(c);
        tasks.add(c1);

        List<Future<Integer>> futures = service.invokeAll(tasks);
        Thread.sleep(2000);
        System.out.println("Future subscribe");
        futures.forEach(e -> {
            try {
                System.out.println(e.get());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }
        });
        service.shutdown();
    }
}
