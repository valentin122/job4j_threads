package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.print("Start loading ... ");
                        for(int i = 0; i <= 100; i++) {
                            System.out.print("\rLoading : " + i  + "%");
                            Thread.sleep(100);
                        }
                        System.out.println();
                        System.out.println("Loaded.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
        System.out.println("Main");
    }
}
