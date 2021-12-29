package ru.job4j.concurrent;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
    Queue<String> process = new LinkedList<>(Arrays.asList("—", "\\", "|", "/"));
        while (!Thread.currentThread().interrupted()) {
            try {
               for (String character : process) {
                Thread.sleep(220);
                    System.out.print("\r load: " + character);
               }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(3000);
        progress.interrupt();
        progress.join();
    }
}
